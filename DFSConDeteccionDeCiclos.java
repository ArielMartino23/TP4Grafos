import java.util.*;
public class DFSConDeteccionDeCiclos<T> {

    private enum Color {BLANCO, AMARILLO, NEGRO};

    private Map<Integer, Color> colores;
    private Map<Integer, Integer> tiempoDescubrimiento;
    private Map<Integer, Integer> tiempoFinalizacion;
    private int tiempoGlobal;
    private boolean hayCiclo;
    
    public boolean tieneCiclo(Grafo<T> grafo){
        colores = new HashMap<>();
        tiempoDescubrimiento = new HashMap<>();
        tiempoFinalizacion = new HashMap<>();
        tiempoGlobal = 0;
        hayCiclo = false;
        
        //Inicializo todos los vertices en BLANCO
        Iterator<Integer> it = grafo.obtenerVertices();
        while(it.hasNext()){
            colores.put(it.next(), Color.BLANCO);
        }

        //Recorro todos los vertices
        for(int vertice : colores.keySet()){
            if(colores.get(vertice) == Color.BLANCO){
                dfsVisit(grafo, vertice);
            }
        }

        return hayCiclo;
    }

    private void dfsVisit(Grafo<T> grafo, int u){
        colores.put(u, Color.AMARILLO);
        tiempoGlobal++;
        tiempoDescubrimiento.put(u, tiempoGlobal);

        Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(u);
        while(itAdyacentes.hasNext()){
            int v = itAdyacentes.next();

            if(colores.get(v) == Color.BLANCO){
                dfsVisit(grafo, v);
            }else if(colores.get(v) == Color.AMARILLO){
                hayCiclo = true;
            }   
        }

        colores.put(u, Color.NEGRO);
        tiempoGlobal++;
        tiempoFinalizacion.put(u, tiempoGlobal);
    }

    public int getTiempoDescubrimiento(int vertice){
        return tiempoDescubrimiento.getOrDefault(vertice, -1);
    }

    public int getTiempoFinalizacion(int vertice){
        return tiempoFinalizacion.getOrDefault(vertice, -1);
    }

}
