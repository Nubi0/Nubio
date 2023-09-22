FROM ubuntu:20.04

# Avoid interactive dialogs during package installation
ENV DEBIAN_FRONTEND=noninteractive 

RUN apt-get update && apt-get install -y python3-dev python3-pip build-essential

# Create a directory for the app
WORKDIR /app

# Copy requirements and install dependencies
COPY requirements.txt .
RUN pip install -r requirements.txt

# Copy the main application file
COPY main.py .

# Command to run the application
CMD ["uvicorn", "main:app", "--reload", "--host", "0.0.0.0", "--port", "8003"]
