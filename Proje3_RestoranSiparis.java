/*
 * Ad Soyad: SEMANUR BUHAN
 * Öğrenci No: 250541007
 * Proje: Restoran Sipariş
 * Tarih: 24.11.2025
 */
import java.util.Scanner;

public class Proje3_RestoranSiparis {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== AKILLI RESTORAN SİPARİŞ SİSTEMİ ===");

        // 1. MENÜ SEÇİMLERİ (GİRDİLER)
        // Ana Yemek Seçimi
        System.out.println("\n--- Ana Yemekler ---");
        System.out.println("1. Izgara Tavuk (85 TL)\n2. Adana Kebap (120 TL)\n3. Levrek (110 TL)\n4. Manti (65 TL)\n0. Istemiyorum");
        System.out.print("Ana Yemek Seciminiz (0-4): ");
        int anaYemekSecim = input.nextInt();

        // Başlangıç Seçimi
        System.out.println("\n--- Baslangiclar ---");
        System.out.println("1. Corba (25 TL)\n2. Humus (45 TL)\n3. Sigara Boregi (55 TL)\n0. Istemiyorum");
        System.out.print("Baslangic Seciminiz (0-3): ");
        int baslangicSecim = input.nextInt();

        // İçecek Seçimi
        System.out.println("\n--- Icecekler ---");
        System.out.println("1. Kola (15 TL)\n2. Ayran (12 TL)\n3. Taze Meyve Suyu (35 TL)\n4. Limonata (25 TL)\n0. Istemiyorum");
        System.out.print("Icecek Seciminiz (0-4): ");
        int icecekSecim = input.nextInt();

        // Tatlı Seçimi
        System.out.println("\n--- Tatlilar ---");
        System.out.println("1. Kunefe (65 TL)\n2. Baklava (55 TL)\n3. Sutlac (35 TL)\n0. Istemiyorum");
        System.out.print("Tatli Seciminiz (0-3): ");
        int tatliSecim = input.nextInt();

        // 2. DİĞER BİLGİLER (Saat, Gün, Öğrenci Durumu)
        System.out.print("\nSaat kacta siparis veriyorsunuz? (0-23): ");
        int saat = input.nextInt();

        System.out.print("Hangi gun? (1=Pzt, ..., 7=Paz): ");
        int gun = input.nextInt();

        System.out.print("Ogrenci misiniz? (Evet icin true, Hayir icin false): ");
        boolean isStudent = input.nextBoolean();

        // 3. HESAPLAMALAR 
      
        double mainPrice = getMainDishPrice(anaYemekSecim);
        double appetizerPrice = getAppetizerPrice(baslangicSecim);
        double drinkPrice = getDrinkPrice(icecekSecim);
        double dessertPrice = getDessertPrice(tatliSecim);

        // Ara toplam
        double araToplam = mainPrice + appetizerPrice + drinkPrice + dessertPrice;

        // Combo kontrolü 
        boolean hasMain = mainPrice > 0;
        boolean hasDrink = drinkPrice > 0;
        boolean hasDessert = dessertPrice > 0;
        boolean isCombo = isComboOrder(hasMain, hasDrink, hasDessert);

        // İndirim hesaplama 
        
        double toplamIndirim = calculateDiscount(araToplam, isCombo, isStudent, saat, drinkPrice, gun);
        
        double sonTutar = araToplam - toplamIndirim;

        // Bahşiş hesaplama 
        double bahsis = calculateServiceTip(sonTutar);

        
        System.out.println("\n======================================");
        System.out.println("          SİPARİŞ FİŞİ");
        System.out.println("======================================");
        
        // Seçilen ürünleri yazdırma 
        if(hasMain) System.out.printf("Ana Yemek\t: %.2f TL\n", mainPrice);
        if(appetizerPrice > 0) System.out.printf("Baslangic\t: %.2f TL\n", appetizerPrice);
        if(hasDrink) System.out.printf("Icecek  \t: %.2f TL\n", drinkPrice);
        if(hasDessert) System.out.printf("Tatli   \t: %.2f TL\n", dessertPrice);
        
        System.out.println("--------------------------------------");
        System.out.printf("ARA TOPLAM\t: %.2f TL\n", araToplam);
        System.out.printf("TOPLAM INDIRIM\t: -%.2f TL\n", toplamIndirim);
        System.out.println("--------------------------------------");
        System.out.printf("ODENECEK TUTAR\t: %.2f TL\n", sonTutar);
        System.out.printf("ONERILEN BAHSIS\t: %.2f TL (%%10)\n", bahsis);
        System.out.println("======================================");
        
        // İndirim detaylarını kullanıcıya bilgi olarak gösterme
        if (isCombo) System.out.println("* Combo Menu İndirimi Uygulandı (%15)");
        if (isHappyHour(saat) && hasDrink) System.out.println("* Happy Hour İçecek İndirimi Uygulandı (%20)");
        if (isStudent && gun <= 5) System.out.println("* Öğrenci İndirimi Uygulandı (%10)");
        if (araToplam > 200) System.out.println("* 200 TL Üzeri Sepet İndirimi Uygulandı (%10)");
        
        input.close();
    }

    

    // 1. Ana Yemek Fiyatı 
    public static double getMainDishPrice(int secim) {
        double price = 0;
        switch (secim) {
            case 1: price = 85; 
              break;  // Izgara Tavuk
            case 2: price = 120; 
              break; // Adana Kebap
            case 3: price = 110; 
              break; // Levrek
            case 4: price = 65; 
              break;  // Manti
            default: price = 0; 
              break;
        }
        return price;
    }

    // 2. Başlangıç Fiyatı 
    public static double getAppetizerPrice(int secim) {
        double price = 0;
        switch (secim) {
            case 1: price = 25; 
              break; // Corba
            case 2: price = 45; 
              break; // Humus
            case 3: price = 55; 
              break; // Sigara Boregi
            default: price = 0; 
              break;
        }
        return price;
    }

    // 3. İçecek Fiyatı 
    public static double getDrinkPrice(int secim) {
        double price = 0;
        switch (secim) {
            case 1: price = 15; 
               break; // Kola
            case 2: price = 12; 
               break; // Ayran
            case 3: price = 35; 
               break; // Meyve Suyu
            case 4: price = 25; 
               break; // Limonata
            default: price = 0;
               break;
        }
        return price;
    }

    // 4. Tatlı Fiyatı 
    public static double getDessertPrice(int secim) {
        double price = 0;
        switch (secim) {
            case 1: price = 65; break; // Kunefe
            case 2: price = 55; break; // Baklava
            case 3: price = 35; break; // Sutlac
            default: price = 0; break;
        }
        return price;
    }

    // 5. Combo Sipariş Kontrolü 
    
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6. Happy Hour Kontrolü 
    
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7. İndirim Hesaplama 
    
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat, double drinkPrice, int gun) {
        double toplamIndirim = 0.0;
        double kalanTutar = tutar;

        //  Combo İndirimi (%15) 
        if (combo) {
            double comboIndirim = tutar * 0.15;
            toplamIndirim += comboIndirim;
            kalanTutar -= comboIndirim; // İndirim düşüldükten sonraki tutar güncellenir
        }

        //  Happy Hour İndirimi (Sadece İçeceklerde %20) 
        if (isHappyHour(saat) && drinkPrice > 0) {
            double happyIndirim = drinkPrice * 0.20;
            toplamIndirim += happyIndirim;
            kalanTutar -= happyIndirim;
        }
        
        //  200 TL Üzeri İndirimi (%10) 
      
        if (tutar > 200) {
             double buyukSepetIndirimi = kalanTutar * 0.10; // Kalan tutar üzerinden uyguluyoruz
             toplamIndirim += buyukSepetIndirimi;
             kalanTutar -= buyukSepetIndirimi;
        }

        //  Öğrenci İndirimi (Hafta içi %10) 
        // Hafta içi: Gün 1,2,3,4,5
        if (ogrenci && gun >= 1 && gun <= 5) {
            double ogrenciIndirim = kalanTutar * 0.10;
            toplamIndirim += ogrenciIndirim;
        }

        return toplamIndirim;
    }

    // 8. Bahşiş Hesaplama 
    // Kural: Hesabın %10'u 
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}


