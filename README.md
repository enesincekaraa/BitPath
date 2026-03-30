# BitPath

BitPath, **URL kısaltma**, **cache yönetimi**, **rate limiting** ve **event-driven analytics** odaklı geliştirilmiş bir backend projesidir.

Bu proje, sadece kısa link üretmekten öte; gerçek dünya backend ihtiyaçlarını düşünerek tasarlanmıştır:

- yüksek erişimli link okuma senaryoları
- Redis ile performans optimizasyonu
- Kafka ile tıklama olaylarının asenkron işlenmesi
- PostgreSQL ile kalıcı veri yönetimi
- Docker Compose ile lokal geliştirme ortamı

---

## 🚀 Projenin Amacı

BitPath’in amacı, production mantığına yakın bir URL shortener sistemi oluşturmaktır.

Bu sistemde:

- kullanıcı kısa link üretir
- kısa link üzerinden yönlendirme yapılır
- yönlendirme sırasında tıklama olayı oluşturulur
- bu olay Kafka üzerinden analytics tarafına aktarılır
- yoğun okuma trafiğinde Redis cache ile veritabanı yükü azaltılır
- rate limiting ile kötüye kullanım senaryoları sınırlandırılır

---

## 🧱 Mimari Yaklaşım

Proje, sorumlulukları birbirinden ayıran bir yapı ile tasarlanmıştır.

### Ana bileşenler

- **URL Shortener Service**
  - kısa link oluşturma
  - kısa link çözme
  - cache kontrolü
  - rate limiting
  - click event üretimi

- **Analytics / Consumer tarafı**
  - Kafka üzerinden click event tüketimi
  - event verisini işleme
  - analytics verisini veritabanına yazma

- **Altyapı servisleri**
  - PostgreSQL
  - Redis
  - Kafka
  - Zookeeper
  - Docker Compose

---

## ⚙️ Kullanılan Teknolojiler

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA

### Veri ve Mesajlaşma
- PostgreSQL
- Redis
- Apache Kafka
- Zookeeper

### Araçlar
- Docker
- Docker Compose
- Maven

---

## ✨ Öne Çıkan Özellikler

- Kısa URL oluşturma
- Kısa URL üzerinden yönlendirme
- Redis tabanlı cache yapısı
- Rate limiting desteği
- Kafka ile click event yayını
- Consumer tarafında event işleme
- PostgreSQL kalıcılığı
- Docker Compose ile kolay lokal kurulum

---

## 🔄 Örnek Akış

Sistemin temel çalışma akışı şu şekildedir:

1. Kullanıcı uzun URL gönderir
2. Sistem benzersiz kısa kod üretir
3. URL verisi PostgreSQL’e kaydedilir
4. Sık erişim için veri Redis’e alınır
5. Kullanıcı kısa linke tıkladığında yönlendirme yapılır
6. Aynı anda bir click event oluşturulur
7. Event Kafka’ya gönderilir
8. Consumer bu event’i okuyup analytics verisini işler

Bu yaklaşım sayesinde:
- yönlendirme akışı hızlı kalır
- analytics işlemleri ana akışı bloklamaz
- sistem daha ölçeklenebilir hale gelir

---

## 🐳 Lokal Ortamda Çalıştırma

### 1) Repoyu klonla

```bash
git clone https://github.com/enesincekaraa/BitPath.git
cd BitPath
```

### 2) Altyapı servislerini ayağa kaldır

```bash
docker compose up -d
```

Bu adım sonrasında aşağıdaki servislerin çalışıyor olması beklenir:

- PostgreSQL
- Redis
- Kafka
- Zookeeper

### 3) Uygulamayı çalıştır

```bash
./mvnw spring-boot:run
```

veya IntelliJ üzerinden ana application class ile başlat.

---

## 📡 API Fikri

### Kısa link oluşturma
`POST /api/urls`

Örnek istek:
```json
{
  "originalUrl": "https://example.com/very/long/path"
}
```

### Kısa link çözme / yönlendirme
`GET /{shortCode}`

Bu istek:
- önce cache kontrolü yapar
- veri yoksa veritabanından çeker
- kullanıcıyı orijinal adrese yönlendirir
- click event üretir

---

## 📈 Teknik Kazanımlar

Bu proje üzerinden özellikle şu backend konuları pratik edilmiştir:

- cache hit / miss mantığı
- Redis entegrasyonu
- rate limiting yaklaşımı
- event-driven communication
- Kafka producer / consumer yapısı
- asenkron analytics akışı
- Docker tabanlı geliştirme ortamı
- production’a yakın servis tasarımı

---

## 🎯 Geliştirme Hedefleri

Projede ilerleyen aşamalarda eklenebilecek geliştirmeler:

- detaylı analytics endpoint’leri
- kullanıcı bazlı link yönetimi
- authentication / authorization
- expiration date desteği
- custom alias desteği
- dashboard yapısı
- test kapsamının artırılması
- CI/CD pipeline entegrasyonu

---

## 💡 Neden Bu Proje Önemli?

BitPath, klasik bir CRUD projesi değildir.

Bu proje; cache, messaging ve analytics gibi gerçek backend ihtiyaçlarını bir araya getirerek daha güçlü bir sistem tasarımı sunmayı hedefler. Özellikle **Redis + Kafka + PostgreSQL** kombinasyonu ile production mantığına yakın bir geliştirme pratiği sağlar.

---

## 👨‍💻 Geliştirici Notu

Bu proje, Java ve Spring Boot ekosisteminde **ölçeklenebilir**, **bakımı kolay** ve **gerçek dünya problemlerine yakın** sistemler geliştirme hedefimin bir parçası olarak oluşturuldu.
