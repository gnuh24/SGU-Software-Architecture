# Kiến trúc phần mềm - Project 1 - Setup các phần mềm hỗ trợ (PostgreQL + Redis + Kafka + Zookeeper) bằng VSC

## **Bước 1: Cấu trúc project cần thiết**
**Lưu ý:** Folder của project **BẮT BUỘC** phải tên là `"sa_project1"` và phải chạy terminal tại folder này -> Nếu sai, lệnh CLI sẽ không nhận path -> lỗi.

```bash
sa_project1/
│
├─ setup/
│   ├─ postgre-init.sql         # Script init dữ liệu MySQL
│   └─ tutorial.md              # File hướng dẫn
│
└─ docker-compose.yml
```


## **Bước 2: Lệnh chạy Docker Compose setup toàn bộ PostgreQL, Redis, Kafka và Zookeeper**

### **Trường hợp 1: Chưa có container/images (Chạy lần đầu)**
```bash
docker-compose up -d
```

Tạo container PostgreQL + Redis + Kafka + Zookeeper mới.

Init dữ liệu tự động chạy.


### **Trường hợp 2: Reset lại setup (Container + Volumes đã tồn tại)**

---


```bash
# Dừng tất cả container và xóa volumes để reset dữ liệu
docker compose down -v

# Chạy lại toàn bộ container theo docker-compose.yml
docker compose up -d
```
