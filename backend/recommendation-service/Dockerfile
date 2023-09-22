FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y python-dev python3-pip build-essential
COPY ["requirements.txt","."]
WORKDIR /
RUN pip install -r requirements.txt
COPY ["main.py","."]
CMD ["uvicorn","main:app","--reload","--host","0.0.0.0","--port","8003"]