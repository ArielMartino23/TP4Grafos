import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

	private Map<Integer, Map<Integer, Arco<T>>> vertices;
	private int cantidadArcos;

	public GrafoDirigido(){
		this.vertices = new HashMap<>();
		this.cantidadArcos = 0;
	}

	@Override
	public void agregarVertice(int verticeId) {
		vertices.putIfAbsent(verticeId, new HashMap<>());
	}

	@Override
	public void borrarVertice(int verticeId) {
		if(vertices.containsKey(verticeId)){
			//Primero borro todos los arcos que iban hacia este vertice
			for(Map<Integer, Arco<T>> adyacentes: vertices.values()){
				if(adyacentes.remove(verticeId) != null){
					cantidadArcos--;
				}
			}
			//Ahora borro todos los arcos salientes de este vertice
			cantidadArcos -= vertices.get(verticeId).size();
			vertices.remove(verticeId);
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)){
			throw new IllegalArgumentException("Ambos vertices deben existir");
		}
		Map<Integer, Arco<T>> adyacentes = vertices.get(verticeId1);
		if(!adyacentes.containsKey(verticeId2)){
			cantidadArcos++;
		}
		adyacentes.put(verticeId2, new Arco<>(verticeId1, verticeId2, etiqueta));
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)){
			Map<Integer, Arco<T>> adyacentes = vertices.get(verticeId1);
			if(adyacentes.remove(verticeId2) != null){
				cantidadArcos--;
			}
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		return vertices.containsKey(verticeId1) && vertices.get(verticeId1).containsKey(verticeId2);
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(existeArco(verticeId1, verticeId2)){
			return vertices.get(verticeId1).get(verticeId2);
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {
		return cantidadArcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(!vertices.containsKey(verticeId)){
			return Collections.emptyIterator();
		}
		return vertices.get(verticeId).keySet().iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> todosLosArcos = new ArrayList<>();
		for(Map<Integer, Arco<T>> adyacentes: vertices.values()){
			todosLosArcos.addAll(adyacentes.values());
		}
		return todosLosArcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(!vertices.containsKey(verticeId)){
			return Collections.emptyIterator();
		}
		return vertices.get(verticeId).values().iterator();
	}

}
