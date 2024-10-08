import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;


public class App {
    public static void main(String[] args) {
        Planet mercuria = new Planet("Mercuria", 0.4, 4879);
        Planet earthia = new Planet("Earthia", 1.0, 12742);
        Planet marsia = new Planet("Marsia", 1.5, 6779);
        Planet venusia = new Planet("Venusia", 0.7, 12104);

        Planet[] planets = { mercuria, earthia, marsia, venusia };

        Arrays.sort(planets);

        determineLightStatus(planets);

        for (Planet planet : planets) {
            System.out.println(planet);
        }

        String svg = generateSVG(planets);
        // Create a file with the SVG content
        try{
            FileOutputStream fos = new FileOutputStream(new File("planets.svg"));
            fos.write(svg.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void determineLightStatus(Planet[] planets) {
        for (int i = 0; i < planets.length; i++) {
            if (i == 0) {
                planets[i].setLightStatus("Full");
                continue;
            }
            Planet current = planets[i];

            long noneCount = Arrays.stream(planets, 0, i)
                    .filter(p -> p.getSizeKm() > current.getSizeKm())
                    .count();

            boolean partial = Arrays.stream(planets, 0, i)
                    .anyMatch(p -> p.getSizeKm() < current.getSizeKm());

            boolean full = Arrays.stream(planets, 0, i)
                    .anyMatch(p -> p.getSizeKm() == current.getSizeKm());

            if (noneCount > 1) {
                planets[i].setLightStatus("None (Multiple Shadows)");
            } else if (noneCount == 1) {
                planets[i].setLightStatus("None");
            } else if (partial) {
                planets[i].setLightStatus("Partial");
            } else if (full) {
                planets[i].setLightStatus("Full");
            }
        }
    }

    private static String generateSVG(Planet[] planets) {
        StringBuilder svg = new StringBuilder();
        svg.append("<svg width=\"800\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\">\n");

        int x = 50;
        for (Planet planet : planets) {
            svg.append("<circle cx=\"").append(x).append("\" cy=\"300\" r=\"")
               .append(planet.getSizeKm() / 1000).append("\" fill=\"blue\" />\n");
            svg.append("<text x=\"").append(x - 10).append("\" y=\"320\" font-size=\"10\" fill=\"black\">")
               .append(planet.getName()).append("</text>\n");
            x += 100;
        }

        svg.append("</svg>");
        return svg.toString();
    }
}
