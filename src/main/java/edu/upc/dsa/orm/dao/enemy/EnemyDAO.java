package edu.upc.dsa.orm.dao.enemy;

import edu.upc.dsa.orm.models.Enemy;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;

import java.sql.SQLException;
import java.util.List;

public interface EnemyDAO {

    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Enemy> findAll();  //Enemies
    public int size();
    public int updateEnemy(Enemy enemy)throws SQLException;

    /***************************************** GET ***********************************************/
    public Enemy getEnemyById(int enemyID) throws SQLException;
    public Enemy getEnemyByName(String name) throws SQLException;

    /*********************************** REGISTRE ENEMICS *******************************************/
    boolean  registerEnemy(EnemyCredentials enemyCredentials) throws SQLException, IllegalAccessException;


}
