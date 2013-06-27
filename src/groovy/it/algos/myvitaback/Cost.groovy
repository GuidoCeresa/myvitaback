package it.algos.myvitaback

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-1-13
 * Time: 09:14
 */
public interface Cost {

    //--sigle dei livelli di authority per la security
    public static String ROLE_PROG = 'ROLE_prog'
    public static String ROLE_ADMIN = 'ROLE_admin'
    public static String ROLE_OSPITE = 'ROLE_ospite'

    //--sigla e password del programmatore
    public static String PROG_NICK= 'gac'
    public static String PROG_PASS = 'fulvia'

    //--sigle degli eventi base da controllare
    public static String EVENTO_NEW = 'new'
    public static String EVENTO_EDIT = 'edit'
    public static String EVENTO_DELETE = 'delete'

} // fine della interfaccia
