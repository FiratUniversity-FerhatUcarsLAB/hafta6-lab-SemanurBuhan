/*
 * Ad Soyad: SEMANUR BUHAN
 * Öğrenci No: 250541007
 * Proje: Sinema Bileti
 * Tarih: 24.11.2025
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {
   
        // Bilet fiyatı hesaplama
    public static boolean isWeekend(int gun){
        boolean isWeekend = false;
        switch (gun) {
            case 1:
            System.out.println("Pazartesi");
                isWeekend = false;
                break;
            case 2:
            System.out.println("Salı");
                isWeekend = false;
                break;
            case 3:
            System.out.println("Çarşamba");
                isWeekend = false;
                break;
            case 4:
            System.out.println("Perşembe");
                isWeekend = false;
                break;
            case 5:
            System.out.println("Cuma");
                isWeekend = false;
                break;
            case 6:
            System.out.println("Cumartesi");
                isWeekend = true;
                break;
            case 7:
            System.out.println("Pazar");
                isWeekend = true;
                break;
            default:
            System.out.println("Geçersiz gün numarası!");
                break;
            }
        return isWeekend;
    }
        
      public static boolean isMatinee(int saat){
        if (saat >= 8 && saat <= 12){
            return true;
        }
        else {
            return false;
        }
      }
      
      public static int calculateBasePrice(int gun, int saat){
        if (gun >=1 && gun <=5 && saat >=8 && saat <=12){
            return 45;           
           }           
            else if (gun >=1 && gun <=5 && saat >12 && saat <=23){
                return 65;}
            else if (gun == 6 || gun == 7 && saat >=8 && saat <=12){
                return 55;}
            else {
                return 85;
            }
        
       }
       
       public static double calculateDiscount(int yas, int meslek, int gun){
          double discount = 0.0;
            switch (meslek) {
                case 1: // Öğrenci
                    System.out.println("Öğrenci");
                    break;
                case 2: // Öğretmen
                    System.out.println("Öğretmen");
                    break;
                case 3: // Diğer
                    System.out.println("Diğer");
                    break;
                default:
                    System.out.println("Geçersiz meslek numarası!");
                    break;
            }
            if (yas <= 12) {
                discount += 0.25;
                } 
                else if (yas >= 65) {
                    discount += 0.30;
                }
                else if (meslek == 1 && gun == 1 && gun == 4) { // Öğrenci ve Pazartesi ve Perşembe
                    discount += 0.20;
                }
                else if (meslek == 1 && gun == 3) { // Öğrenci ve Çarşamba
                    discount += 0.15;
                }
                else if (meslek == 2 && gun == 3) { // Öğretmen ve Çarşamba
                    discount += 0.35;
                }
            return discount;
        }
 
         public static double getFormatExtra(int filmTuru){
            double extra = 0.0;
            switch (filmTuru) {
                case 1: // 2D
                    System.out.println("2D");
                    extra = 0.0;
                    break;
                case 2: // 3D
                    System.out.println("3D");
                    extra = 25.0;
                    break;
                case 3: // IMAX
                    System.out.println("IMAX");
                    extra = 35.0;
                    break;
                case 4: // DX
                    System.out.println("DX");
                    extra = 50.0;
                    break;
                default:
                    System.out.println("Geçersiz film türü!");
                    break;
            }
            return extra;

}

    public static double calculateFinalPrice(int basePrice, double discount, double formatExtra){
        double discountedPrice = basePrice - (basePrice * discount);
        return discountedPrice + formatExtra;
    }


    public static String generateTicketİnfo(int gun, int saat, int yas, int meslek, int filmTuru, double finalPrice){
        String ticketInfo = "Bilet Bilgisi:\n";
        ticketInfo += "Gün: " + gun + "\n";
        ticketInfo += "Saat: " + saat + "\n";
        ticketInfo += "Yaş: " + yas + "\n";
        ticketInfo += "Meslek: " + meslek + "\n";
        ticketInfo += "Film Türü: " + filmTuru + "\n";
        ticketInfo += "Toplam Fiyat: " + finalPrice + " TL\n";
        return ticketInfo;
    }

 public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("1 ile 7 arasında bir gün numarası girin (1=Pazartesi ,..., 7=Pazar): ");
        int gun = in.nextInt();

        System.out.print("8:00 ile 23:00 arasında bir saat girin: ");
        int saat = in.nextInt();

        System.out.print("Yaşınızı girin: ");
        int yas = in.nextInt();

        System.out.print("1 ile 3 arasında meslek numarası girin (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = in.nextInt();

        System.out.print("1 ile 4 arasında bir film türü girin (1=2D, 2=3D, 3=IMAX, 4=DX): ");
        int filmTuru = in.nextInt();

        int basePrice = calculateBasePrice(gun, saat);
        double discount = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(basePrice, discount, formatExtra);

        System.out.println();
        System.out.println("=== Bilet Bilgisi ===");
        System.out.println("Gün: " + gun + (isWeekend(gun) ? " (Hafta Sonu)" : " (Hafta İçi)"));
        System.out.println("Saat: " + saat +(isMatinee(saat) ? " (Matine)" : " (Normal)"));
        System.out.println("Yaş: " + yas); 
        System.out.println("Meslek: " + meslek + (meslek == 1 ? " (Öğrenci)" : meslek == 2 ? " (Öğretmen)" : meslek == 3 ? " (Diğer)" : ""));
        System.out.println("Film Türü: " + filmTuru + (filmTuru == 1 ? " (2D)" : filmTuru == 2 ? " (3D)" : filmTuru == 3 ? " (IMAX)" : filmTuru == 4 ? " (DX)" : ""));
        System.out.printf("Toplam Fiyat: %.2f TL\n", finalPrice);

        in.close();     
    }







}
    
    
    
    
    
    
    
    
    
    
    
    
    

