import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Connexió amb la base de dades
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/gestio_tasques", "usuari", "contrasenya");
            TascaCRUD tascaDAO = new TascaCRUD(conn);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Crear base de dades i taules");
                System.out.println("2. Afegir tasca");
                System.out.println("3. Mostrar totes les tasques");
                System.out.println("4. Mostrar tasca per ID");
                System.out.println("5. Mostrar tasques per nom");
                System.out.println("6. Modificar tasca");
                System.out.println("7. Esborrar tasca");
                System.out.println("8. Crear XML");
                System.out.println("9. Sortir");
                System.out.print("Escull una opció: ");
                int opcio = scanner.nextInt();
                scanner.nextLine(); // Consumim el salt de línia

                switch (opcio) {
                    case 1:
                        tascaDAO.crearBaseDeDades();
                        break;
                    case 2:
                        System.out.print("Nom de la tasca: ");
                        String nom = scanner.nextLine();
                        System.out.print("Descripció: ");
                        String descripcio = scanner.nextLine();
                        System.out.print("Prioritat: ");
                        int prioritat = scanner.nextInt();
                        System.out.print("Data límit (YYYY-MM-DD): ");
                        String data_lim_str = scanner.next();
                        Date data_lim = Date.valueOf(data_lim_str);
                        System.out.print("ID de l'equip: ");
                        int equip_id = scanner.nextInt();
                        tascaDAO.inserirTasca(nom, descripcio, prioritat, data_lim, equip_id);
                        break;
                    case 3:
                        System.out.print("Nombre de tasques per pàgina: ");
                        int limit = scanner.nextInt();
                        System.out.print("Pàgina a mostrar: ");
                        int pagina = scanner.nextInt();
                        int offset = (pagina - 1) * limit;
                        tascaDAO.mostrarTasques(limit, offset).forEach(System.out::println);
                        break;
                    case 4:
                        System.out.print("ID de la tasca: ");
                        int id = scanner.nextInt();
                        System.out.println(tascaDAO.obtenirTascaPerID(id));
                        break;
                    case 5:
                        System.out.print("Nom per buscar: ");
                        String cercaNom = scanner.nextLine();
                        tascaDAO.mostrarTasquesPerNom(cercaNom).forEach(System.out::println);
                        break;
                    case 6:
                        System.out.print("ID de la tasca a modificar: ");
                        int idMod = scanner.nextInt();
                        scanner.nextLine(); // Consumim el salt de línia
                        System.out.print("Nou nom: ");
                        String nouNom = scanner.nextLine();
                        System.out.print("Nova descripció: ");
                        String novaDescripcio = scanner.nextLine();
                        System.out.print("Nova prioritat: ");
                        int novaPrioritat = scanner.nextInt();
                        System.out.print("Nova data límit (YYYY-MM-DD): ");
                        String novaDataStr = scanner.next();
                        Date novaData = Date.valueOf(novaDataStr);
                        tascaDAO.modificarTasca(idMod, nouNom, novaDescripcio, novaPrioritat, novaData);
                        break;
                    case 7:
                        System.out.print("ID de la tasca a esborrar: ");
                        int idEsborrar = scanner.nextInt();
                        tascaDAO.esborrarTasca(idEsborrar);
                        break;
                    case 8:
                        tascaDAO.crearXML();
                        break;
                    case 9:
                        System.out.println("Sortint...");
                        return;
                    default:
                        System.out.println("Opció no vàlida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
