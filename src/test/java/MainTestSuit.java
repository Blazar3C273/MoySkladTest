import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by terron on 23.11.15.
 */
public class MainTestSuit {
    private GraphUtils graphUtils = new GraphUtils();

    @Test
    public void compareFirstLoop() throws Exception {
        int[] firstLoop = LoopFinder.findLoops(graphUtils).get(0);
        assertArrayEquals("arrays must be equals", firstLoop, new int[]{1,2,1});
    }

    @Test
    public void graphIsLoadingFromFile() throws Exception {
        GraphUtils graphUtils = new GraphUtils(Files.newInputStream(Paths.get("./src/test/resources/input.txt")));
        GraphUtils expectedGraphUtils = new GraphUtils();
        expectedGraphUtils.graph.addVertex(1);
        expectedGraphUtils.graph.addVertex(2);
        expectedGraphUtils.graph.addVertex(3);
        expectedGraphUtils.graph.addVertex(4);
        expectedGraphUtils.graph.addVertex(5);
        expectedGraphUtils.graph.addVertex(6);
        //expectedGraphUtils.graph.addVertex(7);
        //expectedGraphUtils.graph.addVertex(8);

        expectedGraphUtils.graph.addEdge(1,2);
        expectedGraphUtils.graph.addEdge(2,1);
        expectedGraphUtils.graph.addEdge(3,4);
        expectedGraphUtils.graph.addEdge(5,6);
        expectedGraphUtils.graph.addEdge(6,5);
        //expectedGraphUtils.graph.addEdge(7,8);
        //expectedGraphUtils.graph.addEdge(8,5);

        //charset? another characters
        //bigInteger
        //направление зависимостей
        System.out.println("expectedGraphUtils = " + expectedGraphUtils.graph);
        System.out.println("graphUtils = " + graphUtils.graph);
        System.out.println(graphUtils.getLoops());
        assertEquals("graphUtils must be the same", expectedGraphUtils, graphUtils);
    }

    @Test
    public void doStuff() throws Exception {
        DirectedGraph<Integer,DefaultEdge> graph = new DefaultDirectedGraph<Integer,DefaultEdge>(DefaultEdge.class);

    }

    @Test
    public void graphComparation() throws Exception {
        DirectedGraph<Integer,Integer> graph1,graph2,graph3;
        graph1 = new DefaultDirectedGraph<Integer, Integer>(Integer.class);
        graph2 = new DefaultDirectedGraph<Integer, Integer>(Integer.class);
        graph3 = new DefaultDirectedGraph<Integer, Integer>(Integer.class);

        for (int i = 0; i < 10; i++) {
            graph1.addVertex(i);
            graph2.addVertex(i);
        }

        assertNotEquals(graph1,null);
        assertNotEquals(graph1,graph3);
        assertEquals("graphs must be equals", graph1,graph2);

    }
}
