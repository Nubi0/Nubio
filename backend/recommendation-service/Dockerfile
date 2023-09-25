FROM ubuntu:20.04

# Avoid interactive dialogs during package installation
ENV DEBIAN_FRONTEND=noninteractive 

RUN apt-get update && apt-get install -y python3-dev python3-pip build-essential

# Create a directory for the app
WORKDIR /app

# Copy project files (main.py, app directory, and others)
COPY . .

# Copy requirements and install dependencies
RUN pip install -r requirements.txt

# Command to run the application
CMD ["uvicorn", "main:app", "--reload", "--host", "0.0.0.0", "--port", "8003"]
