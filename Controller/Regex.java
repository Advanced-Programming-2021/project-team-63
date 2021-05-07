package Controller;

import java.util.regex.*;

public class Regex {
    public static String allRegexPatterns;
    public static String enterMenu="^\\s*menu enter (.+)\\s*$";
    public static String exitMenu="^\\s*menu exit\\s*$";
    public static String showCurrentMenu="^\\s*menu show-current\\s*$";

    //////////////////making new user
    public static String createUser_unp_="^\\s*user create (?:--username|-u) (.+) (?:--nickname|-n) (.+) (?:--password|-p) (.+)\\s*$";
    public static String createUser_upn_="^\\s*user create (?:--username|-u) (.+) (?:--password|-p) (.+) (?:--nickname|-n) (.+)\\s*$";
    public static String createUser_nup_="^\\s*user create (?:--nickname|-n) (.+) (?:--username|-u) (.+) (?:--password|-p) (.+)\\s*$";
    public static String createUser_npu_="^\\s*user create (?:--nickname|-n) (.+) (?:--password|-p) (.+) (?:--username|-u) (.+)\\s*$";
    public static String createUser_pnu_="^\\s*user create (?:--password|-p) (.+) (?:--nickname|-n) (.+) (?:--username|-u) (.+)\\s*$";
    public static String createUser_pun_="^\\s*user create (?:--password|-p) (.+) (?:--username|-u) (.+) (?:--nickname|-n) (.+)\\s*$";
    //////////////////login

    public static String loginUser_pu_="^\\s*user login (?:--password|-p) (.+) (?:--username|-u) (.+)\\s*$";
    public static String loginUser_up_="^\\s*user login (?:--username|-u) (.+) (?:--password|-p) (.+)\\s*$";

    /////////////////
    public static String loguotuser="^\\s*user loguot\\s*$";
    public static String showScorboard="^\\s*scoreboard show\\s*$";
    ////////////////change profile nickname
    public static String changeProfileNickname="^\\s*profile change (?:--nickname|-n) (.+)\\s*$";
    ///////////////change profile pass
    public static String changeProfilePass_pcn_="^\\s*profile change (?:--password|-p) (?:--current|-c) (.+) (?:--new|-n) (.+)\\s*$" ;
    public static String changeProfilePass_cpn_="^\\s*profile change (?:--current|-c) (.+) (?:--password|-p) (?:--new|-n) (.+)\\s*$";
    public static String changeProfilePass_pnc_="^\\s*profile change (?:--password|-p) (?:--new|-n) (.+) (?:--current|-c) (.+)\\s*$" ;
    public static String changeProfilePass_cnp_="^\\s*profile change (?:--current|-c) (.+) (?:--new|-n) (.+) (?:--password|-p)\\s*$";
    public static String changeProfilePass_npc_="^\\s*profile change (?:--new|-n) (.+) (?:--password|-p) (?:--current|-c) (.+)\\s*$" ;
    public static String changeProfilePass_ncp_="^\\s*profile change (?:--new|-n) (.+) (?:--current|-c) (.+) (?:--password|-p)\\s*$";
    //////////////
    public static String showCard="^\\s*card show (.+)\\s*$";
    public static String createDeck="^\\s*deck create (.+)\\s*$";
    public static String deleteDeck="^\\s*deck delete (.+)\\s*$";
    public static String setDeckActivated="^\\s*deck set-activate (.+)\\s*$";
    ////////////add cart to deck

    public static String addCardToDeck_cds_="^\\s*deck add-card (?:--card|-c) (.+) (?:--deck|-d) (.+)(?: (--side)|(-side))*\\s*$";
    public static String addCardToDeck_csd_="^\\s*deck add-card (?:--card|-c) (.+)(?: (--side)|(-side))* (?:--deck|-d) (.+)\\s*$";
    public static String addCardToDeck_dsc_="^\\s*deck add-card (?:--deck|-d) (.+)(?: (--side)|(-side))* (?:--card|-c) (.+)\\s*$";
    public static String addCardToDeck_dcs_="^\\s*deck add-card (?:--deck|-d) (.+) (?:--card|-c) (.+)(?: (--side)|(-side))*\\s*$";
    public static String addCardToDeck_scd_="^\\s*deck add-card (?:(--side)|(-side))* (?:--card|-c) (.+) (?:--deck|-d) (.+)\\s*$";
    public static String addCardToDeck_sdc_="^\\s*deck add-card (?:(--side)|(-side))* (?:--deck|-d) (.+) (?:--card|-c) (.+)\\s*$";

    ///////////delete card from deck

    public static String deleteCardOfDeck_cds_="^\\s*deck rm-card (?:--card|-c) (.+) (?:--deck|-d) (.+)(?: (--side)|(-side))*\\s*$";
    public static String deleteCardOfDeck_csd_="^\\s*deck rm-card (?:--card|-c) (.+)(?: (--side)|(-side))* (?:--deck|-d) (.+)\\s*$";
    public static String deleteCardOfDeck_dsc_="^\\s*deck rm-card (?:--deck|-d) (.+)(?: (--side)|(-side))* (?:--card|-c) (.+)\\s*$";
    public static String deleteCardOfDeck_dcs_="^\\s*deck rm-card (?:--deck|-d) (.+) (?:--card|-c) (.+)(?: (--side)|(-side))*\\s*$";
    public static String deleteCardOfDeck_scd_="^\\s*deck rm-card (?:(--side)|(-side))* (?:--card|-c) (.+) (?:--deck|-d) (.+)\\s*$";
    public static String deleteCardOfDeck_sdc_="^\\s*deck rm-card (?:(--side)|(-side))* (?:--deck|-d) (.+) (?:--card|-c) (.+)\\s*$";

    ///////////////
    public static String showCard="^\\s*deck show --all\\s*$";

    public static String showDeck_dc_="^\\s*deck show(?: (--side)|(-side))* (?:--deck-name|-d) (.+)\\s*$";
    public static String showDeck_ds_="^\\s*deck show (?:--deck-name|-d) (.+)(?: (--side)|(-side))*\\s*$";

    public static String showAllCard="^\\s*deck show --cards\\s*$";

    //////////////

    public static String buyCard="^\\s*shop buy (.+)\\s*$";

    //////////////// /////????????????????????????????????????currect names

    public static String startNewDuel="^\\s*duel (?:--second-player|-sc|--ai|-a) (?:--new|-n) (?:--rounds|-r) (\\d)\\s*$";
    public static String deleteCardOfDeck_csd_="^\\s*duel (?:--second-player|-sc|--ai|-a) (?:--rounds|-r) (\\d) (?:--new|-n)\\s*$";
    public static String deleteCardOfDeck_dsc_="^\\s*duel (?:--new|-n) (?:--rounds|-r) (\\d) (?:--second-player|-sc|--ai|-a)\\s*$";
    public static String deleteCardOfDeck_dcs_="^\\s*duel (?:--new|-n) (?:--second-player|-sc|--ai|-a) (?:--rounds|-r) (\\d)\\s*$";
    public static String deleteCardOfDeck_scd_="^\\s*duel (?:--rounds|-r) (\\d) (?:--second-player|-sc|--ai|-a) (?:--new|-n)\\s*$";
    public static String deleteCardOfDeck_sdc_="^\\s*duel (?:--rounds|-r) (\\d) (?:--new|-n) (?:--second-player|-sc|--ai|-a)\\s*$";

    /////////////
    public static String selectMonsterOfOurs="^\\s*select --monster (\\d)\\s*$";

    public static String selectMonsterOfOpponent="^\\s*select (?:--monster|-m) (\\d) (?:--opponent|-o)\\s*$";
    public static String selectMonsterOfOpponent="^\\s*select (?:--opponent|-o) (?:--monster|-m) (\\d)\\s*$";








}
