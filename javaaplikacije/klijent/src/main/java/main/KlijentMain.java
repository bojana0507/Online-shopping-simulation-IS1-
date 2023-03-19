package main;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class KlijentMain {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public interface Api {
    @GET("api/{nesto}?")
    Call<String> api(@Path("nesto") String nesto);
    
    @GET("api/upit/1")
    Call<String> upit1(
        @Query("naziv") String naziv,
        @Query("drzava") String drzava,
        @Query("pb") String pb);
    
    @GET("api/upit/2")
    Call<String> upit2(
        @Query("korisnickoime") String korime,
        @Query("sifra") String sifra,
        @Query("ime") String ime,
        @Query("prezime") String prezime,
        @Query("adresa") String adresa,
        @Query("novac") BigDecimal novac,
        @Query("gradID") Integer grad);
    
    @GET("api/upit/3")
    Call<String> upit3(
        @Query("korisnickoime") String korime,
        @Query("novac") BigDecimal novac);
    
    @GET("api/upit/4")
    Call<String> upit4(
        @Query("korisnickoime") String korime,
        @Query("adresa") String adresa,
        @Query("gradID") Integer grad);
    
    @GET("api/upit/5")
    Call<String> upit5(
        @Query("naziv") String naziv,
        @Query("nadkategorijaId") int nadkatId);
    
    @GET("api/upit/6")
    Call<String> upit6(
        @Query("naziv") String naziv,
        @Query("opis") String opis,
        @Query("cena") BigDecimal cena,
        @Query("popust") int popust,
        @Query("kategorijaID") int kategorijaID,
        @Query("prodavacID") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/7")
    Call<String> upit7(
        @Query("artikalID") int idArt,
        @Query("novaCena") BigDecimal nadkatId,
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/8")
    Call<String> upit8(
        @Query("artikalID") int idArt,
        @Query("popust") int procenat,
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/9")
    Call<String> upit9(
        @Query("artikalID") int idArt,
        @Query("kolicina") int kolicina,
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/10")
    Call<String> upit10(
        @Query("artikalID") int idArt,
        @Query("kolicina") int kolicina,
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/11")
    Call<String> upit11(
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/12")
    Call<String> upit12();
    
    @GET("api/upit/13")
    Call<String> upit13();
    
    @GET("api/upit/14")
    Call<String> upit14();
    
    @GET("api/upit/15")
    Call<String> upit15(
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/16")
    Call<String> upit16(
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/17")
    Call<String> upit17(
        @Query("korime") String korime,
        @Query("sifra") String sifra);
    
    @GET("api/upit/18")
    Call<String> upit18();
    
    @GET("api/upit/19")
    Call<String> upit19();
    
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to my client application!");
        
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/server/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        Api api = retrofit.create(Api.class);
        
        boolean done = false;
        while (!done) {
            System.out.println("Please select an option:");
            System.out.println("0. Quit");
            System.out.println("1. Kreiranje grada");
            System.out.println("2. Kreiranje korisnika");
            System.out.println("3. Dodavanje novca korisniku");
            System.out.println("4. Menjanje adrese i grada korisnika");
            System.out.println("5. Kreiranje kategorije");
            System.out.println("6. Kreiranje artikla");
            System.out.println("7. Menjanje cene artikla");
            System.out.println("8. Postavljanje popusta za artikal");
            System.out.println("9. Dodavanje artikala u određenoj količini u korpu");
            System.out.println("10. Brisanje artikla u određenoj količini iz korpe");
            System.out.println("11. Plaćanje, koje obuhvata kreiranje transakcije, kreiranje narudžbine sa njenim stavkama, i brisanje sadržaja iz korpe");
            System.out.println("12. Dohvatanje svih gradova");
            System.out.println("13. Dohvatanje svih korisnika");
            System.out.println("14. Dohvatanje svih kategorija");
            System.out.println("15. Dohvatanje svih artikala koje prodaje korisnik koji je poslao zahtev");
            System.out.println("16. Dohvatanje sadržaja korpe korisnika koji je poslao zahtev");
            System.out.println("17. Dohvatanje svih narudžbina korisnika koji je poslao zahtev");
            System.out.println("18. Dohvatanje svih narudžbina");
            System.out.println("19. Dohvatanje svih transakcija");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    upit1(api);
                    break;
                case "2":
                    upit2(api);
                    break;
                case "3":
                    upit3(api);
                    break;
                case "4":
                    upit4(api);
                    break;
                case "5":
                    upit5(api);
                    break;
                case "6":
                    upit6(api);
                    break;
                case "7":
                    upit7(api);
                    break;
                case "8":
                    upit8(api);
                    break;
                case "9":
                    upit9(api);
                    break;
                case "10":
                    upit10(api);
                    break;
                case "11":
                    upit11(api);
                    break;
                case "12":
                    upit12(api);
                    break;
                case "13":
                    upit13(api);
                    break;
                case "14":
                    upit14(api);
                    break;
                case "15":
                    upit15(api);
                    break;
                case "16":
                    upit16(api);
                    break;
                case "17":
                    upit17(api);
                    break;
                case "18":
                    upit18(api);
                    break;
                case "19":
                    upit19(api);
                    break;
                case "20":
                    test(api, "test");
                    break;
                case "0":
                    done = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void test(Api api, String x1) {
        try {            
            Call<String> call = api.api(x1);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit1(Api api) {
        System.out.println("Naziv grada:");
        String naziv = scanner.nextLine();
        System.out.println("Drzava grada:");
        String drzava = scanner.nextLine();
        System.out.println("Postanski broj grada:");
        String pb = scanner.nextLine();
        try {            
            Call<String> call = api.upit1(naziv, drzava, pb);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit2(Api api) {
        System.out.println("Korisnicko ime:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sif = scanner.nextLine();
        System.out.println("Ime:");
        String ime = scanner.nextLine();
        System.out.println("Prezime:");
        String prez = scanner.nextLine();
        System.out.println("Adresa:");
        String adr = scanner.nextLine();
        System.out.println("Novac:");
        BigDecimal nov = new BigDecimal(scanner.nextLine());
        System.out.println("GradID:");
        Integer gid = Integer.parseInt(scanner.nextLine());
        try {            
            Call<String> call = api.upit2(korime, sif, ime, prez, adr, nov, gid);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit3(Api api) {
        System.out.println("Korisnicko ime:");
        String korime = scanner.nextLine();
        System.out.println("Novac za dodavanje:");
        BigDecimal nov = new BigDecimal(scanner.nextLine());
        
        try {            
            Call<String> call = api.upit3(korime, nov);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    private static void upit4(Api api) {
        System.out.println("Korisnicko ime:");
        String korime = scanner.nextLine();
        System.out.println("Nova adresa:");
        String adr = scanner.nextLine();
        System.out.println("Nov GradID:");
        Integer gid = Integer.parseInt(scanner.nextLine());
        try {            
            Call<String> call = api.upit4(korime, adr, gid);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit5(Api api) {
        System.out.println("Naziv kategorije:");
        String naziv = scanner.nextLine();
        System.out.println("Id nadkategorije(0 ako ne postoji):");
        Integer nadkatId = Integer.parseInt(scanner.nextLine());
        try {            
            Call<String> call = api.upit5(naziv, nadkatId);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit6(Api api) {
        System.out.println("Korisnicko ime prodavca:");
        String prodId = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        System.out.println("Naziv artikla:");
        String naziv = scanner.nextLine();
        System.out.println("Cena:");
        BigDecimal cena = new BigDecimal(scanner.nextLine());
        System.out.println("Opis:");
        String opis = scanner.nextLine();
        System.out.println("Id kategorije:");
        Integer katId = Integer.parseInt(scanner.nextLine());
        System.out.println("Popust:");
        Integer popust = Integer.parseInt(scanner.nextLine());

        try {
            Call<String> call = api.upit6(naziv, opis, cena, popust, katId, prodId, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void upit7(Api api) {
        System.out.println("Korisnicko ime prodavca:");
        String prodId = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        System.out.println("Id artikla:");
        Integer artId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nova cena:");
        BigDecimal novaCena = new BigDecimal(scanner.nextLine());
        try {
            Call<String> call = api.upit7(artId, novaCena, prodId, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void upit8(Api api) {
        System.out.println("Korisnicko ime prodavca:");
        String prodId = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        System.out.println("Id artikla:");
        Integer artId = Integer.parseInt(scanner.nextLine());
        System.out.println("Popust (u procentima):");
        int popust = Integer.parseInt(scanner.nextLine());
        try {
            Call<String> call = api.upit8(artId, popust, prodId, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void upit9(Api api) {
        System.out.println("Id korisnika:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        System.out.println("Id artikla:");
        Integer artId = Integer.parseInt(scanner.nextLine());
        System.out.println("Kolicina:");
        Integer kolicina = Integer.parseInt(scanner.nextLine());
        
        try {
            Call<String> call = api.upit9(artId, kolicina, korime, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit10(Api api) {
        System.out.println("Id korisnika:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        System.out.println("Id artikla:");
        Integer artId = Integer.parseInt(scanner.nextLine());
        System.out.println("Kolicina:");
        Integer kolicina = Integer.parseInt(scanner.nextLine());
        
        try {
            Call<String> call = api.upit10(artId, kolicina, korime, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit11(Api api) {
        System.out.println("Id korisnika:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        try {            
            Call<String> call = api.upit11(korime, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit12(Api api) {
        try {            
            Call<String> call = api.upit12();
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    private static void upit13(Api api) {
        try {            
            Call<String> call = api.upit13();
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit14(Api api) {
        try {            
            Call<String> call = api.upit14();
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void upit15(Api api) {
        System.out.println("Id korisnika:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        try {            
            Call<String> call = api.upit15(korime, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void upit16(Api api) {
        System.out.println("Id korisnika:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        try {            
            Call<String> call = api.upit16(korime, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void upit17(Api api) {
        System.out.println("Id korisnika:");
        String korime = scanner.nextLine();
        System.out.println("Sifra:");
        String sifra = scanner.nextLine();
        try {            
            Call<String> call = api.upit17(korime, sifra);
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit18(Api api) {
        try {            
            Call<String> call = api.upit18();
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void upit19(Api api) {
        try {            
            Call<String> call = api.upit19();
            Response<String> response = call.execute();
            String apiResponse = response.body();
            System.out.println(apiResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
