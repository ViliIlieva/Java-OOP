package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //всички полета
        //getField - връща public полета
        List<Field> fields = Arrays.asList(RichSoilLand.class.getDeclaredFields());//връща абсолютно всички полета

        Map<Commands, List<Field>> modifiersFieldMap = getMap(fields);

        fillMapWithFields(fields, modifiersFieldMap);

		Consumer<Field> printer = field -> System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());

        String input = scanner.nextLine();
        while (!input.equals("HARVEST")) {
            switch (input) {
                case "public":
                    modifiersFieldMap.get(Commands.PUBLIC).forEach(field -> printer.accept(field));
                    break;
                case "private":
					modifiersFieldMap.get(Commands.PRIVATE).forEach(field -> printer.accept(field));
                    break;
                case "protected":
					modifiersFieldMap.get(Commands.PROTECTED).forEach(field -> printer.accept(field));
                    break;
                case "all":
					modifiersFieldMap.get(Commands.ALL).forEach(field -> printer.accept(field));
                    break;
            }
            input = scanner.nextLine();
        }
    }

    private static void fillMapWithFields(List<Field> fields, Map<Commands, List<Field>> modifiersFieldMap) {
        for (Field field : fields) {
            String modifier = Modifier.toString(field.getModifiers());
            switch (modifier) {
                case "public":
                    modifiersFieldMap.get(Commands.PUBLIC).add(field);
                    break;
                case "private":
                    modifiersFieldMap.get(Commands.PRIVATE).add(field);
                    break;
                case "protected":
                    modifiersFieldMap.get(Commands.PROTECTED).add(field);
                    break;
            }
        }
    }

    private static Map<Commands, List<Field>> getMap(List<Field> fields) {
        Map<Commands, List<Field>> modifiersFieldMap = new LinkedHashMap<>();
        modifiersFieldMap.put(Commands.PUBLIC, new ArrayList<>());
        modifiersFieldMap.put(Commands.PRIVATE, new ArrayList<>());
        modifiersFieldMap.put(Commands.PROTECTED, new ArrayList<>());
        modifiersFieldMap.put(Commands.ALL, fields);
        return modifiersFieldMap;
    }
}
