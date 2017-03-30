# CampaignAllocator

Problem definition and goal
---
Given monthly inventory amount and a list of campaigns with curstomer name, unit amount of impressions and corresponding proce, find the best set of campaigns that will generate maximum revenue not exceeding monthly inventory. 
Response time goals:
- not more than 3 seconds (best case)
- 1 minute (worst case)
Memory goals:
- Heap size less than 4GB

Algorithm/ Solution
---
This is a case of classic 0-1 Knapsack problem with repeating elements. Dynamic Programming (DP) works best with O(inventoryAmount * numberOfCampaigns) runtime and O(inventoryAmount) memory. However when the numbers are too large it becomes a NP-Complete, even NP-Hard problem. Therefore, different algorithms should be used based on request data such as DP for tractable size and branch-bound approach for large numbers within close range having tractable number of campaigns.

DP Approach
---
DP solution is implemented in `CampaignAllocationAlgorithmDP` class with some further optimization. Steps
-- Scale the inventory numbers dividing by Greatest Common Divisor of monthly inventory amount and all the unit impression amount of campaigns. This will significantly reduce the run time and memory unless GCD is 1. If the monthly inventory is still too large after scaling, consider scale factor = 100 which will make the DP solution feasible under current constraints but lose data upto 2 digits a nd find a near optimal solution.
Runt time is O(inventoryAmount/ inventoryScaleFactor * numberOfCampaigns) and memory is 2 * O(inventoryAmount/ inventoryScaleFactor)

Sample results
---
Test case 1 response time `33ms`

Input
```shell
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "Postman-Token: aba79d72-a914-6eec-6d99-ad59ec6280f0" -d '{
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
}
' "http://127.0.0.1:8080/campaign-allocation"
```
Output
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

Test case 2 response time `2288ms`

Input
```shell
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "Postman-Token: 8c433918-be3b-db7f-0fbe-3d94de0d3190" -d '{
	"monthlyInventory": 50000000,
		"campaigns": [{
          "customerName": "customer1",
          "impressions": 1,
          "revenue": 0
        }, {
          "customerName": "customer2",
          "impressions": 2,
          "revenue": 2
        },{
          "customerName": "customer3",
          "impressions": 3,
          "revenue": 2
        },{
          "customerName": "customer4",
          "impressions": 70000,
          "revenue": 71000
        },{
          "customerName": "customer5",
          "impressions": 49000000,
          "revenue": 50000000
        }]
}
' "http://127.0.0.1:8080/campaign-allocation"
```

Output
```javascript
{
  "totalImpressions": 50000000,
  "totalRevenue": 51014000,
  "campaigns": [
    {
      "customerName": "customer1",
      "count": 0,
      "impressions": 1,
      "revenue": 0,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer2",
      "count": 10000,
      "impressions": 2,
      "revenue": 2,
      "totalImpressions": 20000,
      "totalRevenue": 20000
    },
    {
      "customerName": "customer3",
      "count": 0,
      "impressions": 3,
      "revenue": 2,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer4",
      "count": 14,
      "impressions": 70000,
      "revenue": 71000,
      "totalImpressions": 980000,
      "totalRevenue": 994000
    },
    {
      "customerName": "customer5",
      "count": 1,
      "impressions": 49000000,
      "revenue": 50000000,
      "totalImpressions": 49000000,
      "totalRevenue": 50000000
    }
  ]
}
```

Test case 3 response time `22ms`
Input
```shell
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "Postman-Token: c00af6b4-ba69-14d9-e487-22bc4cb93e30" -d '{
	"monthlyInventory": 2000000000 ,
		"campaigns": [{
          "customerName": "customer1",
          "impressions": 1000000,  
          "revenue": 5000
        }, {
          "customerName": "customer2",
          "impressions": 2000000,
          "revenue": 9000
        },{
          "customerName": "customer3",
          "impressions": 3000000,  
          "revenue": 20000
        }]
}' "http://127.0.0.1:8080/campaign-allocation"
```

Output
```javascript
{
  "totalImpressions": 2000000000,
  "totalRevenue": 13330000,
  "campaigns": [
    {
      "customerName": "customer1",
      "count": 2,
      "impressions": 1000000,
      "revenue": 5000,
      "totalImpressions": 2000000,
      "totalRevenue": 10000
    },
    {
      "customerName": "customer2",
      "count": 0,
      "impressions": 2000000,
      "revenue": 9000,
      "totalImpressions": 0,
      "totalRevenue": 0
    },
    {
      "customerName": "customer3",
      "count": 666,
      "impressions": 3000000,
      "revenue": 20000,
      "totalImpressions": 1998000000,
      "totalRevenue": 13320000
    }
  ]
}
```



How to start the CampaignAllocator application
---

1. Run `mvn clean install` or `mvn package` to build your application
1. Start application with `java -jar target/campaign-allocator-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
