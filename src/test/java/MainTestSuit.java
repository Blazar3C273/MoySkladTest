
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by terron on 23.11.15.
 */
public class MainTestSuit {
    private Graph graph = new Graph();

    @Test
    public void compareFirstLoop() throws Exception {
        int[] firstLoop = LoopFinder.findLoops(graph).get(0);
        assertArrayEquals("arrays must be equals", firstLoop, new int[]{1,2,1});
    }

    @Test
    public void graphIsLoadingFromFile() throws Exception {
        Graph graph = new Graph(Files.newInputStream(Paths.get("./src/test/resources/input.txt")));
        Graph expectedGraph  = new Graph();
        expectedGraph.addNode(1,2);
        expectedGraph.addNode(2,1);
        expectedGraph.addNode(3,4);
        expectedGraph.addNode(5,6);
        expectedGraph.addNode(6,5);

        assertEquals("graph must be the same",expectedGraph,graph);

    }
}
