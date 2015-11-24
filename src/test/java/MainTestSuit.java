import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Stepanenko Anatolii
 * @version 0.1
 * Created on 24.11.15
 */
public class MainTestSuit {
    private GraphUtils graphUtils = new GraphUtils();


    @Test
    public void graphIsLoadingFromFile() throws Exception {
        GraphUtils graphUtils = new GraphUtils(Files.newInputStream(Paths.get("./src/test/resources/input.txt")));
        GraphUtils expectedGraphUtils = new GraphUtils();
        expectedGraphUtils.getGraph().addVertex(1);
        expectedGraphUtils.getGraph().addVertex(2);
        expectedGraphUtils.getGraph().addVertex(3);
        expectedGraphUtils.getGraph().addVertex(4);
        expectedGraphUtils.getGraph().addVertex(5);
        expectedGraphUtils.getGraph().addVertex(6);
        //expectedGraphUtils.getGraph().addVertex(7);
        //expectedGraphUtils.getGraph().addVertex(8);

        expectedGraphUtils.getGraph().addEdge(1, 2);
        expectedGraphUtils.getGraph().addEdge(2, 1);
        expectedGraphUtils.getGraph().addEdge(3, 4);
        expectedGraphUtils.getGraph().addEdge(5, 6);
        expectedGraphUtils.getGraph().addEdge(6, 5);
        //expectedGraphUtils.getGraph().addEdge(7,8);
        //expectedGraphUtils.getGraph().addEdge(8,5);

        //charset? another characters
        //bigInteger
        //направление зависимостей
        System.out.println("expectedGraphUtils = " + expectedGraphUtils.getGraph());
        System.out.println("graphUtils = " + graphUtils.getGraph());
        System.out.println(graphUtils.getLoops());
        assertEquals("graphUtils must be the same", expectedGraphUtils, graphUtils);
    }

    @Test
    public void graphComparation() throws Exception {
        DirectedGraph<Integer, Integer> graph1, graph2, graph3;
        graph1 = new DefaultDirectedGraph<>(Integer.class);
        graph2 = new DefaultDirectedGraph<>(Integer.class);
        graph3 = new DefaultDirectedGraph<>(Integer.class);

        for (int i = 0; i < 10; i++) {
            graph1.addVertex(i);
            graph2.addVertex(i);
        }

        assertNotEquals(graph1, null);
        assertNotEquals(graph1, graph3);
        assertEquals("graphs must be equals", graph1, graph2);

    }
}
