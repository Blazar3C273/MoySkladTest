import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.alg.cycle.TiernanSimpleCycles;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GraphUtils {
    public DefaultDirectedGraph<Integer, DefaultEdge> graph;

    private static final Pattern pattern = Pattern.compile("(\\d+)\\s(\\d+)$");

    public GraphUtils(InputStream inputStream) {
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        //каждую строку разбить на 2 числа и этими числами заинитить граф
        br.lines().filter(GraphUtils::isValidString).forEach(s -> addVertexByString(s,graph));
    }

    private static void addVertexByString(String s, DefaultDirectedGraph<Integer, DefaultEdge> graph) {
        Integer v1,v2,splitterIndex;
        splitterIndex = s.indexOf(' ');
        v1 = Integer.valueOf(s.substring(0,splitterIndex));
        v2 = Integer.valueOf(s.substring(splitterIndex+1));
        if (!graph.containsVertex(v1))
            graph.addVertex(v1);
        if (!graph.containsVertex(v2))
            graph.addVertex(v2);
        graph.addEdge(v1,v2);

    }

    private static boolean isValidString(String s) {
        if (s == null)
            return false;
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(GraphUtils.class))
            return graph.equals(((GraphUtils)obj).graph);
        return false;
    }

    public GraphUtils() {
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public List<List<Integer>> getLoops() {
        TiernanSimpleCycles tsc = new TiernanSimpleCycles(graph);
        return tsc.findSimpleCycles();

    }

    @Override
    public String toString() {
        return "GraphUtils{" +
                "graph=" + graph +
                '}';
    }
}
