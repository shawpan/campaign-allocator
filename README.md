# CampaignAllocator

How to start the CampaignAllocator application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/campaign-allocator-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

POST request
---

```shell
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "Postman-Token: 4eb3902c-4a38-0189-d26b-1651208c37bc" -d '{
	"monthlyInventory": 32356000,
		"campaigns": [{
          "customerName": "customer1",
          "impressions": 2000000,
          "revenue": 200
        }, {
          "customerName": "customer2",
          "impressions": 3500000,
          "revenue": 400
        },{
          "customerName": "customer3",
          "impressions": 2300000,
          "revenue": 210
        },{
          "customerName": "customer4",
          "impressions": 8000000,
          "revenue": 730
        },{
          "customerName": "customer5",
          "impressions": 10000000,
          "revenue": 1000
        },{
          "customerName": "customer6",
          "impressions": 1500000,
          "revenue": 160
        },{
          "customerName": "customer7",
          "impressions": 1000000,
          "revenue": 100
        }]
}' "http://127.0.0.1:8080/campaign-allocation"
```

Output
---
```javascript
{
  "totalImpressions": 32000000,
  "totalRevenue": 3620,
  "campaigns": [
    {
      "customerName": "customer1",
      "count": 0,
      "impressions": 2000000,
      "revenue": 200,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer2",
      "count": 8,
      "impressions": 3500000,
      "revenue": 400,
      "totalImpressions": 28000000,
      "totalRevenue": 3200
    },
    {
      "customerName": "customer3",
      "count": 0,
      "impressions": 2300000,
      "revenue": 210,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer4",
      "count": 0,
      "impressions": 8000000,
      "revenue": 730,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer5",
      "count": 0,
      "impressions": 10000000,
      "revenue": 1000,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer6",
      "count": 2,
      "impressions": 1500000,
      "revenue": 160,
      "totalImpressions": 3000000,
      "totalRevenue": 320
    },
    {
      "customerName": "customer7",
      "count": 1,
      "impressions": 1000000,
      "revenue": 100,
      "totalImpressions": 1000000,
      "totalRevenue": 100
    }
  ]
}
```
