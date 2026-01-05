package simulation;

import java.util.HashMap;
import java.util.Random;

import Dao.ContextDao;
import domain.Component;
import domain.Context;
import domain.Machine;

/**
 * The goal here is to simulate a large number of machines sending their 
 * information to the SQL server to simulate a real production environment for the application 
 * (the simulator can be disabled depending on the database chosen by the user in the .env file).|
 *
 * L'objectif est ici de simuler un grand nombre de machines envoyant leurs informations 
 * au serveur SQL afin de simuler un environnement de production réel pour l'application 
 * (le simulateur peut être désactivé en fonction de la base de données choisie par l'utilisateur dans le fichier .env).
 * 
 * @author FIGUEIRAS Jossua
 */
public class Simulator {
    private Context context;
    private HashMap<Integer, Integer> prevTemp;
    private HashMap<Integer, Integer> prevLoad;
    private HashMap<Integer, Integer> loadList;
    private HashMap<Integer, Integer> tempList;
    private int baseLoad;
    private int baseTemp;
    private Random random;
    private boolean continu;
 
    public Simulator(){
        this.context = new Context();
        this.prevTemp = new HashMap<Integer, Integer>();
        this.prevLoad = new HashMap<Integer, Integer>();
        this.loadList = new HashMap<Integer, Integer>();
        this.tempList = new HashMap<Integer, Integer>();
        this.baseLoad = 75;
        this.baseTemp = 60;
        this.random = new Random();
        this.continu = true;

        System.out.print("\nStart Simulation\n");

        while(continu){
            try{
                this.context.updateComponentList();
                this.context.updateMachinesList();

                this.nextComponentLoad();
                this.newMachineTemp();

                //load
                ContextDao.sendHashMapLoad(this.loadList);
                this.prevLoad = this.loadList;
                this.loadList = new HashMap<Integer, Integer>();

                //temp
                ContextDao.sendHashMapTemp(this.tempList);
                this.prevTemp = this.tempList;
                this.tempList = new HashMap<Integer, Integer>();

                Thread.sleep(10000);
            }catch(Exception e){
                System.out.print("Error simulator : " + e);
            }
        }  
    }
    /**
     * For each online machine, its temperature is simulated.|
     * Pour chaque machine en ligne, sa température est simulée.
     */
    private void newMachineTemp(){
        for(Machine m : this.context.getMachines()){
            if(m.getStatus().equals("Online")){
                double salt = 0.6 + this.random.nextDouble();
                double newTemp;
                if(this.prevTemp.get(m.getId()) == null){
                    double baseT;
                    switch(m.whoami("default")){
                        case "Compute" :  baseT = this.baseTemp;
                        break;
                        case "GpuCompute" : baseT = this.baseTemp + 15;
                        break;
                        default :  baseT = this.baseTemp - 15;
                        break;
                    }
                    newTemp = baseT * salt;
                }else{
                    newTemp = this.prevTemp.get(m.getId()) * salt;
                }

                if(newTemp > 120){
                    newTemp = 115;
                }
                this.tempList.put(m.getId(), (int)newTemp);

            }
        }
    }
    /**
     * For each component used in an online machine, its load percentage is simulated.|
     * Pour chaque composant utilisé dans une machine en ligne, son pourcentage de charge est simulé.
     */
    private void nextComponentLoad(){
        for(Component c : this.context.getListComponents()){
            Machine machine = Machine.getMachine(c.getMachineId());
            if (machine != null && "Online".equals(machine.getStatus())){
                double salt = 0.6 + this.random.nextDouble();
                double newLoad;
                
                if(this.prevLoad.get(c.getId()) != null){
                    newLoad = this.prevLoad.get(c.getId()) * salt;
                }else{
                    newLoad = this.baseLoad * salt;
                }
                //special event
                int predictionSize = 100;
                int predictionRandom = this.random.nextInt(predictionSize);
                if(predictionRandom == (predictionSize/2)+1){
                    int randomValue = this.random.nextInt(2) + 1;
                    newLoad += randomValue * 10;
                }
                if(newLoad>100){
                    newLoad = 100;
                }else if(newLoad < 0){
                    newLoad = 0;
                }
                this.loadList.put(c.getId(), (int)newLoad);
            }
        }
    }
}
