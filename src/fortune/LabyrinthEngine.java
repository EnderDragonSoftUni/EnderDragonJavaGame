package fortune;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LabyrinthEngine implements Runnable {


    private boolean running;
    protected int timeDelay = 100;
    protected Labyrinth labyrinth;
    protected GemaMap map;
    protected Smiley smiley;

    protected KeyAdapter keyListener = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == 37) {
                smiley.directionX = -1;
            }
            if (e.getKeyCode() == 39) {
                smiley.directionX = 1;
            }
            if (e.getKeyCode() == 38) {
                smiley.directionY = -1;
            }
            if (e.getKeyCode() == 40) {
                smiley.directionY = 1;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
                smiley.directionX = 0;
            }
            if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
                smiley.directionY = 0;
            }
        }
    };

    protected MouseAdapter mouseListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            int startTileX = (int) Math.floor((Math.abs(smiley.posX - smiley.posRenderX) + e.getX()) / BaseTile.SIZE),
                    startTileY = (int) Math.floor((Math.abs(smiley.posY - smiley.posRenderY) + e.getY()) / BaseTile
                            .SIZE);
            if (tileIsWalkable(startTileX, startTileY)) {
                MapPath mapPath = makePath(smiley.posX / BaseTile.SIZE, smiley.posY / BaseTile.SIZE, startTileX,
                        startTileY);
                smiley.mapPath = mapPath;
            }
        }
    };

    public void start() {
        if (running) {
            return;
        }
        running = true;
        labyrinth = new Labyrinth();
        smiley = new Smiley();
        setMap(1);
        setListener();
        new Thread(this).start();
    }

    public MapPath makePath(int startX, int startY, int endX, int endY) {
        MapFindingPath mfp = new MapFindingPath();
        mfp.setcheckPoint(new IMapCheckPoint() {
            @Override
            public boolean check(int x, int y) {
                return tileIsWalkable(x, y);
            }
        });

        if (mfp.findPath(startX, startY, endX, endY)) {
            return mfp.getPath();
        }

        return null;
    }

    public void setMap(int mapId) {
        if (mapId == 1) {
            map = new Map1();
        } else if (mapId == 2) {
            map = new Map2();
        } else if (mapId == 3) {
            map = new Map3();
        }

        smiley.mapPath = null;
        smiley.posX = BaseTile.SIZE;
        smiley.posY = BaseTile.SIZE;
        smiley.posRenderX = BaseTile.SIZE;
        smiley.posRenderY = BaseTile.SIZE;
    }

    public void stop() {
        unSetListener();
        running = false;
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }


    @Override
    public void run() {
        while (running) {
            try {
                TimeUnit.MILLISECONDS.sleep(timeDelay);
            } catch (InterruptedException ex) {
                Logger.getLogger(LabyrinthEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            update();
        }
    }

    public void setListener() {
        labyrinth.setFocusable(true);
        labyrinth.addKeyListener(keyListener);
        labyrinth.addMouseListener(mouseListener);
    }

    public void unSetListener() {
        labyrinth.setFocusable(false);
        labyrinth.removeKeyListener(keyListener);
        labyrinth.removeMouseListener(mouseListener);
    }

    protected void update() {
        checkingChangeMap();
        labyrinth.removeRenders();
        int mapW = map.getWidth(),
                mapH = map.getHeight(),
                x, y, tileId,
                widthTile = 18,
                heightTile = 12,
                cWidthTile = widthTile / 2 - 1,
                cHeightTile = heightTile / 2 - 1,
                offsetX = (smiley.posX - smiley.posRenderX) % BaseTile.SIZE,
                offsetY = (smiley.posY - smiley.posRenderY) % BaseTile.SIZE,
                startTileX = (int) Math.floor(Math.abs(smiley.posX - smiley.posRenderX) / BaseTile.SIZE),
                startTileY = (int) Math.floor(Math.abs(smiley.posY - smiley.posRenderY) / BaseTile.SIZE),
                endTileX = widthTile + startTileX > mapW ? mapW : widthTile + startTileX,
                endTileY = heightTile + startTileY > mapH ? mapH : heightTile + startTileY;

        boolean movePlayerX = (smiley.posX / BaseTile.SIZE < cWidthTile || mapW - smiley.posX / BaseTile.SIZE <
                cWidthTile + 1),
                movePlayerY = (smiley.posY / BaseTile.SIZE < cHeightTile || mapH - smiley.posY / BaseTile.SIZE <
                        cHeightTile + 2);

        BaseTile tile;
        for (y = startTileY; y < endTileY; y++) {
            for (x = startTileX; x < endTileX; x++) {
                tileId = map.getTileId(x, y);
                tile = BaseTile.getTileById(tileId);

                tile.posX = (x - startTileX) * BaseTile.SIZE;
                tile.posY = (y - startTileY) * BaseTile.SIZE;
                tile.posX -= offsetX;
                tile.posY -= offsetY;

                labyrinth.addRender(tile);
            }
        }

        labyrinth.addRender(smiley);
        if ((smiley.directionX != 0 || smiley.directionY != 0) && accessMove()) {
            smiley.posX += smiley.directionX * smiley.speed;
            smiley.posY += smiley.directionY * smiley.speed;

            if (movePlayerX) {
                smiley.posRenderX += smiley.directionX * smiley.speed;
            }
            if (movePlayerY) {
                smiley.posRenderY += smiley.directionY * smiley.speed;
            }
        }

        labyrinth.repaint();
    }

    public void checkingChangeMap() {
        int tileX = smiley.posX / BaseTile.SIZE,
                tileY = smiley.posY / BaseTile.SIZE,
                tileId = map.getTileId(tileX, tileY);
        BaseTile tile = BaseTile.getTileById(tileId);
        if (tile.door > 0) {
            setMap(tile.door);
        }
    }

    protected boolean accessMove() {
        int left, right, top, down;
        boolean isWalkable = true;

        left = (int) Math.ceil((smiley.posX) / BaseTile.SIZE);
        right = (int) Math.floor((smiley.posX + smiley.width - 1) / BaseTile.SIZE);
        top = (int) Math.ceil((smiley.posY + smiley.speed * smiley.directionY) / BaseTile.SIZE);
        down = (int) Math.floor((smiley.posY + smiley.height + smiley.speed * smiley.directionY - 1) / BaseTile.SIZE);

        if (smiley.directionY == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(right, top))) {
            isWalkable = false;
        } else if (smiley.directionY == 1 && !(tileIsWalkable(left, down) && tileIsWalkable(right, down))) {
            isWalkable = false;
        }

        left = (int) Math.ceil((smiley.posX + smiley.speed * smiley.directionX) / BaseTile.SIZE);
        right = (int) Math.floor((smiley.posX + smiley.width + smiley.speed * smiley.directionX - 1) / BaseTile.SIZE);
        top = (int) Math.ceil((smiley.posY) / BaseTile.SIZE);
        down = (int) Math.floor((smiley.posY + smiley.height - 1) / BaseTile.SIZE);

        if (smiley.directionX == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(left, down))) {
            isWalkable = false;
        } else if (smiley.directionX == 1 && !(tileIsWalkable(right, top) && tileIsWalkable(right, down))) {
            isWalkable = false;
        }

        return isWalkable;
    }

    protected boolean tileIsWalkable(int x, int y) {
        BaseTile tile = BaseTile.getTileById(map.getTileId(x, y));
        return (tile != null && tile.isWalkable);
    }
}
