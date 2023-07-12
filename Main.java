import java.io.*;
import java.nio.file.FileSystemException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            AddUser();
            System.out.println("Данные сохранены");
        }catch (FileSystemException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }

    }

    public static void AddUser() throws Exception{
        System.out.println("Фамилия Имя Отчество номертелефона, разделенные пробелом");

        String text = "";
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            text = bf.readLine();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        String[] array = text.split(" ");

        if (array.length != 4)
            throw new Exception("Введены не все данные");     

        String surname = array[0];
        String name = array[1];
        String patronymic = array[2];

        long phone = 0;
        try {
            phone = Long.parseLong(array[3]);
        }catch (NumberFormatException  e){
            throw new NumberFormatException ("Неверный формат телефона");
        }

        String fileName = surname.toLowerCase() + ".txt";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)){
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s", surname, name, patronymic, phone));
        }catch (IOException e){
            throw new FileSystemException("Возникла ошибка при работе с файлом");
        }

    }
}