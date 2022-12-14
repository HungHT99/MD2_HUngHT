package ra.config;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Catalog;
import ra.bussiness.imp.BookImp;
import ra.bussiness.imp.CatalogImp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static ra.config.Message.*;
import static ra.data.ConstantRegexAndUrl.*;

public class CheckValidate {
    public static String strValidate(Scanner sc, String str){
        String strOutput;
        do {
            strOutput = sc.nextLine();
            if (strOutput.trim().matches(str)){
                break;
            }else {
                System.out.println(FORMATERROR);
            }
        }while (true);
        return strOutput;
    }
    public static int choiceNumber(Scanner sc, int a, int b) {                        // choice int number
        int number;
        do {
            System.out.print(YOURCHOICE);
            try {
                number = Integer.parseInt(sc.nextLine());
                if (number >= a && number <= b) {
                    break;
                } else {
                    System.out.println(ERRCHOICENUMBER + a + "-" + b + " :");
                }
            } catch (Exception e) {
                System.out.println(FORMATERROR);
            }
        } while (true);
        return number;
    }
    public static String checkbookName(Scanner sc){
        BookImp bookImp = new BookImp();
        List<Book> bookList = bookImp.readformFile();
        String strName;
        do {
            strName = strValidate(sc, REGEXFULLNAME);
            boolean check = false;
            for (Book book:bookList) {
                if (book.getBookName().equals(strName)){
                    check = true;
                    break;
                }
            }
            if (check){
                System.out.println(NAMEERROR1);
            }else {
                break;
            }
        }while (true);
        return strName;
    }
    public static String bookStates(Scanner sc){
        String states;
        System.out.println("Tinh trang sach: \n"+
                "1.Moi   2.Binh thuong   3.Cu");
        int choice2 = choiceNumber(sc, 1,2);
        if (choice2==1){
            states="Moi";
        } else if (choice2==2) {
            states="Binh thuong";
        }else {
            states="Cu";
        }
        return states;
    }

    public static Catalog catalogForBook(Scanner sc){
        CatalogImp catalogImp = new CatalogImp();
        Catalog catalogReturn = new Catalog();
        List<Catalog> catalogList = catalogImp.readformFile();
        boolean check = false;
        for (Catalog catalog:catalogList) {
            System.out.printf("%d.%s\n", catalog.getCatalogId(), catalog.getCatalogName());
        }
        int choice = choiceNumber(sc,1,catalogList.size());
        for (Catalog catalog: catalogList) {
            if (catalog.getCatalogId() == choice){
                catalogReturn = catalog;
                break;
            }
        }
        return  catalogReturn;
    }

    public static List<String> addAuthor(Scanner sc){
        List<String> authorList = new ArrayList<>();
        do {
            System.out.println("Nhap ten tac gia");
            String authorName = strValidate(sc, REGEXFULLNAME);
            boolean check = true;
            for (String author:authorList) {
                if (author.equals(authorName)){
                    check = false;
                    break;
                }
            }
            if (check){
                authorList.add(authorName);
            }else {
                System.out.println(NAMEERROR1);
            }
            System.out.println("Ban muon them tac gia khong? \n"+
                    "1. Co    2 . Khong");
            System.out.println("");
            int choice = choiceNumber(sc,1,2);
            if (choice !=1){
                break;
            }
        }while (true);
            return authorList;
    }
    public  static String checkCatalogName(Scanner sc){
        CatalogImp catalogImp = new CatalogImp();
        List<Catalog> catalogList = catalogImp.readformFile();
        String strName;
        do {
            strName = strValidate(sc, REGEXFULLNAME);
            boolean check = false;
            for (Catalog catalog : catalogList) {
                if (catalog.getCatalogName().equals(strName)) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println(NAMEERROR1);
            } else {
                break;
            }
        } while (true);
        return strName;
    }
    public static boolean choiceBooleanStatus(Scanner sc) {                             // check choice status
        boolean status = true;
        System.out.println("1." + STATUS1 + "       2." + STATUS3);
        int choice = choiceNumber(sc, 1, 2);
        if (choice == 2) {
            status = false;
        }
        return status;
    }
    public static Date dateValidate(Scanner sc) {                                      // validate date
        String intputDate = strValidate(sc, REGEXDATE);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = dateFormat.parse(intputDate);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    public static ArrayList<Book> bookListCard(Scanner sc) {                    // check list book add card
        BookImpl bookImpl = new BookImpl();
        List<Book> bookListReturn = new ArrayList<>();
        do {
            List<Book> bookList = bookImpl.readFromFile();
            for (Book book : bookList) {
                if (book.getBookStatus().equals(STATUS1)) {
                    System.out.printf("%s---%s\n", book.getBookId(), book.getBookName());
                }
            }
            System.out.print("Nh???p Id s??ch mu???n th??m: ");
            String choiceId = strValidate(sc, REGEXBOOKID);
            for (Book book : bookList) {
                if (book.getBookId().equals(choiceId)) {
                    if (bookListReturn.size() == 0) {
                        bookListReturn.add(book);
                        book.setBookquantity(book.getBookquantity() - 1);
                        bookImpl.writetoFile(bookList);
                        break;
                    } else {
                        boolean check = false;
                        for (Book book1 : bookListReturn) {
                            if (book1.getBookId().equals(choiceId)) {
                                check = true;
                                break;
                            }
                        }
                        if (check) {
                            System.out.println("S??ch ???? c?? trong th??? m?????n, h??y th??m s??ch kh??c.");
                            break;
                        } else {
                            bookListReturn.add(book);
                            book.setBookquantity(book.getBookquantity() - 1);
                            bookImpl.writeToFile(bookList);
                            break;
                        }
                    }
                }
            }
            System.out.println("b???n c?? mu???n th??m s??ch kh??c kh??ng?");
            System.out.println(" 1. c??      2. kh??ng");
            int choice = choiceNumber(sc, 1, 2);
            if (choice != 1) {
                break;
            }
        } while (true);

        return (ArrayList<Book>) bookListReturn;
    }
}
