#Java version
Employee mikroservis projesi java 21 sürümüyle çalışmaktadır.
#Nasıl çalışır
projeyi git üzerinden indirip bir eksik olup olmadığını kontrol ettikten sonra eğer sorun yoksa 
java version ve maven version(3.x ve üzeri) kontrol edilmedilir.Daha sonra projeyi run edebilirsiniz.

_2024-08-11T18:08:43.615+03:00  INFO 22200 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8090 (http) with context path ''
2024-08-11T18:08:43.630+03:00  INFO 22200 --- [           main] c.example.employee.EmployeeApplication   : Started EmployeeApplication in 5.404 seconds (process running for 5.953)_

Buna benzer bir log varsa proje başarı ile çalışmıştır.

#H2 veritabanı kullanılmaktadır
H2 veritabanıyla alakalı tüm ayarlar application.properties dosyası içerisindedir.
http://localhost:8090/h2-console üzerinden application properties dosyasında bulunan bilgiler ile giriş yapılıp veritabanı kontrol edilebilir.


#FeignClient İnfo
**Employee mikroservisi** ile **Department mikroservisi** feignclient ile iletişim kurmaktadır.
Employee projesi **8090** portu üzerinden çalışmaktadır. İstenirse application.properties üzerinden server.port=8090 değiştirilebilir.
Department mikroservisi **8080** portu üzerinden çalışmaktadır.

#FeignClient Kullanımı
FeignUtil üzerinde de belirtildiği gibi kullanılmak istenilmediği durumlarda
url ="http://localhost:8080/api/department" url adresi değiştirelecek url e göre düzenlenmesi gerekmektedir.
Bu işlemleri local üzerinden denediğim için adresler localhost olarak tanımlıdır.Bir server üzerinde çalıştılacağı zaman bir baseurl yapılıp kullanılması daha uygun olacaktır.
