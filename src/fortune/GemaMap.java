/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fortune;

/**
 * Класс карты игры.
 * По сути, карта является многомерным числовым массивом.
 * Массив можно хранить в виде   переменной, 
 * а можно загружать из файла.
 * 
 */
public abstract class GemaMap {
    
    protected int[][] map;
    

    
    /**
     * Проверка наличия плитки на карте.
     * @param x
     * @param y
     * @return 
     */
    public boolean hasTile(int x, int y) {
       return (x >= 0 && y >= 0 && y <  getHeight() && x <  getWidth()); 
    }
    
    /**
     * Получить ИД плитки.
     * @param x
     * @param y
     * @return 
     */
    public int getTileId(int x, int y) {
        return hasTile(x,y) ? map[y][x] : 0;
    }
    
   
    
    /**
     * Количество плиток на карте по высоте.
     * @return 
     */
    public int getHeight() {
        return map.length;
    }
    
    /**
     * Количество плиток на карте по ширине.
     * @return 
     */
    public int getWidth() {
        return map[0].length;
    }
}
