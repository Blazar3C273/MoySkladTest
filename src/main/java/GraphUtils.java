import org.jgrapht.alg.cycle.TiernanSimpleCycles;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GraphUtils {
    private DefaultDirectedGraph<Integer, DefaultEdge> graph;

    public DefaultDirectedGraph<Integer, DefaultEdge> getGraph() {
        return graph;
    }
    //Патерн, по которому проверяется валидность строк. Невалидные строки игнорируются.
    private static final Pattern pattern = Pattern.compile("(\\d+)\\s(\\d+)$");

    public static void main(String[] args) {
        try {
            //Читаем имя файла из stdin. Тут столкнулся с не точной формулировкой задания.
            //В ней сказано "файл читать из stdin". непонятно что именно читать. Имя файла? Полный путь? Содержимое?
            //Если все таки содержимое то при чем здесь файл? Тогда это просто чтение даных из stdin.
            //если все таки последний вариант корректный, то создание экземпляра выгладит так:
            // GraphUtils graphUtils = new GraphUtils(System.in);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            GraphUtils graphUtils = new GraphUtils(Files.newInputStream(Paths.get(br.readLine())));

            for (List<Integer> integers : graphUtils.getLoops()) {
                for (Integer integer : integers) {
                    System.out.print(integer + " ");
                }
                System.out.println("");
            }

        } catch (IOException e) {
            if (e.getClass().equals(NoSuchFileException.class))
                System.out.println("No file with name \"" + e.getMessage() + "\".");
        }
    }

    public GraphUtils(InputStream inputStream) {
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        //отсеять невалидные строки
        //каждую строку разбить на 2 числа и этими числами построить граф
        br.lines().filter(GraphUtils::isValidString).forEach(s -> addVertexByString(s, graph));
    }

    private static void addVertexByString(String s, DefaultDirectedGraph<Integer, DefaultEdge> graph) {
        Integer v1, v2, splitterIndex;
        splitterIndex = s.indexOf(' ');
        v1 = Integer.valueOf(s.substring(0, splitterIndex));
        v2 = Integer.valueOf(s.substring(splitterIndex + 1));

        if (!graph.containsVertex(v1))
            graph.addVertex(v1);
        if (!graph.containsVertex(v2))
            graph.addVertex(v2);

        graph.addEdge(v1, v2);

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
            return graph.equals(((GraphUtils) obj).graph);
        return false;
    }

    public GraphUtils() {
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public List<List<Integer>> getLoops() {
        TiernanSimpleCycles tsc = new TiernanSimpleCycles(graph);

        List<List<Integer>> cycles = (List<List<Integer>>) tsc.findSimpleCycles();
        for (List<Integer> cycle : cycles) {
            cycle.add(cycle.get(0));
        }
        return cycles;
    }

    @Override
    public String toString() {
        return "GraphUtils{" +
                "graph=" + graph +
                '}';
    }


}
