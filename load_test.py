import asyncio
import aiohttp
import time

API_URL = "http://localhost:9091/kafka/api/v1/products/add"

async def send_request(session, payload):
    async with session.post(API_URL, json=payload) as response:
        return await response.text()

async def main():
    async with aiohttp.ClientSession() as session:
        tasks = []
        total_requests = 60000  # Total number of requests to be made
        requests_per_second = 3000  # Number of requests per second

        start_time = time.time()

        for i in range(total_requests):
            payload = {
                "id": i,
                "title": f"Product {i}",
                "price": i * 10.0,
                "description": f"Description for product {i}",
                "category": "category",
                "image": f"https://example.com/img/product_{i}.jpg",
                "rating": {
                    "id": i,
                    "rate": (i % 5) + 1,
                    "count": i * 10
                }
            }
            task = asyncio.create_task(send_request(session, payload))
            tasks.append(task)

            if (i + 1) % requests_per_second == 0:
                await asyncio.gather(*tasks)
                tasks = []
                await asyncio.sleep(1)  # wait for 1 second before sending the next batch

        if tasks:
            await asyncio.gather(*tasks)

        end_time = time.time()
        total_time = end_time - start_time

        # Print statement to indicate all data has been inserted and the time taken
        print(f"All data has been inserted. Total time taken: {total_time:.2f} seconds.")

if __name__ == "__main__":
    asyncio.run(main())