import java.util.*;
public class BFS<T> {

    private enum Estado {NO_VISITADO, VISITADO};

    private Map<Integer, Estado> estados;

    public void recorrer(Grafo<T> grafo){
        estados = new HashMap<>();
        Queue<Integer> cola = new LinkedList<>();

        //Inicializo todos los vertices como NO_VISITADO
        Iterator<Integer> it = grafo.obtenerVertices();
        while(it.hasNext()){
            int vertice = it.next();
            estados.put(vertice, Estado.NO_VISITADO);
        }

        //Ejecuto BFS desde cada vertice NO_VISITADO
        for(int vertice : estados.keySet()){
            if(estados.get(vertice) == Estado.NO_VISITADO){
                bfsDesdeVertice(grafo, vertice, cola);
            }
        }
    }

    private void bfsDesdeVertice(Grafo<T> grafo, int s, Queue<Integer> cola){
        estados.put(s, Estado.VISITADO);
        cola.add(s);

        while(!cola.isEmpty()){
            int x = cola.poll();

            Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(x);
            while(itAdyacentes.hasNext()){
                int y = itAdyacentes.next();

                if(estados.get(y) == Estado.NO_VISITADO){
                    estados.put(y, Estado.VISITADO);
                    cola.add(y);
                }
            }
        }
    }

}
