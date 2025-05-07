import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		GrafoDirigido<Float> grafito = new GrafoDirigido<>();

        // Agregar vértices
        for (int i = 1; i <= 5; i++) {
            grafito.agregarVertice(i);
        }

        // Agregar arcos
        grafito.agregarArco(1, 2, 1.5F);
        grafito.agregarArco(1, 3, 2.5F);
        grafito.agregarArco(2, 4, 3.5F);
        grafito.agregarArco(3, 4, 4.5F);
        grafito.agregarArco(4, 5, 5.5F);
        grafito.agregarArco(5, 1, 6.5F);

        // Mostrar cantidad de vertices y arcos
        System.out.println("Cantidad de vertices: " + grafito.cantidadVertices()); // 5
        System.out.println("Cantidad de arcos: " + grafito.cantidadArcos()); // 6

        // Mostrar todos los vértices
        System.out.println("\nVertices:");
        Iterator<Integer> itVertices = grafito.obtenerVertices();
        while (itVertices.hasNext()) {
            System.out.println(itVertices.next());
        }

        // Mostrar adyacentes de 1
        System.out.println("\nAdyacentes de 1:");
        Iterator<Integer> itAdy = grafito.obtenerAdyacentes(1);
        while (itAdy.hasNext()) {
            System.out.println(itAdy.next());
        }

        // Mostrar todos los arcos
        System.out.println("\nArcos:");
        Iterator<Arco<Float>> itArcos = grafito.obtenerArcos();
        while (itArcos.hasNext()) {
            Arco<Float> arco = itArcos.next();
            System.out.println(arco.getVerticeOrigen() + " -> " + arco.getVerticeDestino() + " (Etiqueta: " + arco.getEtiqueta() + ")");
        }

        // Buscar un arco específico
        System.out.println("\nEtiqueta del arco de 1 a 3: " + grafito.obtenerArco(1, 3).getEtiqueta());

        DFSConDeteccionDeCiclos<Float> dfs = new DFSConDeteccionDeCiclos<>();
        boolean tieneCiclo = dfs.tieneCiclo(grafito);
        System.out.println("\n¿El grafo tiene ciclo? " + (tieneCiclo ? "Sí" : "No"));

	}

}
