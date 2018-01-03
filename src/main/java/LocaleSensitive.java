import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yasin_000 on 29.12.2017.
 */

public class LocaleSensitive {

    final static ArgsClass argsClass = new ArgsClass();

    public static void main(String[] args) {

        JCommander jCommander = new JCommander(argsClass);
        jCommander.setProgramName("Locale Sensitive String Sort");

        try {
            jCommander.parse(args);
        }catch (ParameterException e){
            System.err.println("something wrong with args");
            e.printStackTrace();
        }

        if (argsClass.getParameters().isEmpty())
            System.err.println("a file is expected");
        else{
            if (argsClass.getLocale().equals("tr-TR"))
                readFile(argsClass.getParameters().get(0), "tr-TR");
            else
                readFile(argsClass.getParameters().get(0), "en-US");

        }


    }

    private static void readFile(String fileName, String langName) {
        try {
            List<String> fileList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

            String[] stringarr = new String[fileList.size()];
            String[] array = fileList.toArray(stringarr);


            if (langName.equals("tr-TR")){
                if (argsClass.isReverse()){
                    if (argsClass.isIgnoreCase())
                        sortIgnoreCase(fileList);
                    else
                        sortReverse(langName, fileList);
                }
                else{
                    final Collator coll = Collator.getInstance(Locale.forLanguageTag(langName));
                    if (argsClass.isIgnoreCase()){

                        Arrays.sort(array, new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                return coll.compare(o1, o2);
                            }
                        });

                        for (int i = 0; i < array.length; i++) {
                            for (int j = 0; j < array.length; j++) {
                                if (array[j].toLowerCase().equals(array[i]) ){
                                    Collections.swap(Arrays.asList(array), i, j);
                                }
                                if (array[j].toUpperCase().equals(array[i])){
                                    Collections.swap(Arrays.asList(array), i, j);
                                }
                            }
                        }

                        fileList = Arrays.stream(array).collect(Collectors.toList());

                    }else{
                        Arrays.sort(array, new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                return coll.compare(o1, o2);
                            }
                        });

                        fileList = Arrays.stream(array).collect(Collectors.toList());
                    }
                }
            }
            else if (langName.equals("en-US")){
                if (argsClass.isReverse()){
                    if (argsClass.isIgnoreCase()){
                        final Collator coll = Collator.getInstance(Locale.forLanguageTag(langName));
                        Arrays.sort(array, new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                return coll.compare(o1, o2);
                            }
                        });

                        for (int i = 0; i < array.length; i++) {
                            for (int j = 0; j < array.length; j++) {
                                if (array[j].toLowerCase().equals(array[i]) ){
                                    Collections.swap(Arrays.asList(array), i, j);
                                }
                                if (array[j].toUpperCase().equals(array[i])){
                                    Collections.swap(Arrays.asList(array), i, j);
                                }
                            }
                        }

                        fileList = Arrays.stream(array).collect(Collectors.toList());
                        Collections.reverse(fileList);
                    }else {
                        Collections.sort(fileList);
                        Collections.reverse(fileList);
                    }
                }else{
                    if (argsClass.isIgnoreCase()){

                        final Collator coll = Collator.getInstance(Locale.forLanguageTag(langName));
                        Arrays.sort(array, new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                return coll.compare(o1, o2);
                            }
                        });

                        for (int i = 0; i < array.length; i++) {
                            for (int j = 0; j < array.length; j++) {
                                if (array[j].toLowerCase().equals(array[i]) ){
                                    Collections.swap(Arrays.asList(array), i, j);
                                }
                                if (array[j].toUpperCase().equals(array[i])){
                                    Collections.swap(Arrays.asList(array), i, j);
                                }
                            }
                        }

                        fileList = Arrays.stream(array).collect(Collectors.toList());
                    }
                    else {
                        Arrays.sort(array);
                        fileList = Arrays.stream(array).collect(Collectors.toList());
                    }
                }
            }

            if (argsClass.getOutputFile() == null){
                fileList.forEach(System.out::println);
            }
            else if (argsClass.getOutputFile() != null){
                Files.write(Paths.get(argsClass.getOutputFile() ), fileList);
                System.out.println("Output file is succesfully created");
            }



        } catch (IOException e) {
            System.out.println("You need to make the text file UTF-8 format");
            e.printStackTrace();
        }

    }

    private static void sortIgnoreCase(List<String> list) {
        Collections.sort(list, String::compareToIgnoreCase);
    }

    private static void sortReverse(String langName, List<String> list) {
        Collections.sort(list, (o1, o2) -> Collator.getInstance(Locale.forLanguageTag(langName))
                .compare(o1.toLowerCase(), o2.toLowerCase()));
        Collections.reverse(list);
    }

}
