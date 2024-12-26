#!/bin/bash

# Step 1: install python dependencies
echo "Installing python dependencies..."
pip install aiohttp

# Check if aiohttp is installed
if [ $? -eq 0 ]; then
    echo "Python dependencies installed successfully."
else
    echo "Failed to install Python dependencies."
    exit 1
fi

# Step 2: Run the load test script
echo "Running the load test..."
python load_test.py

# Check if the load test was successful
if [ $? -eq 0 ]; then
    echo "Load test completed successfully."
else
    echo "Load test failed."
    exit 1
fi