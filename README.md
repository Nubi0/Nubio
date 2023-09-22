# NUBIO

## ✈ 배포 주소

#### 개발 버전 : Version 0.1.0 </br>  
#### 프론트 서버: https://nubi0.com (미배포) </br>  
#### 백엔드 서버: </br>  
- kong-server : http://ec2-43-201-30-17.ap-northeast-2.compute.amazonaws.com:8000 </br>
- Authentication-service : http://ec2-13-209-41-128.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/index.html </br> 
- enjoy-service : http://ec2-13-209-41-128.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/index.html (미배포) </br> 
- safe-service : http://ec2-13-209-41-128.ap-northeast-2.compute.amazonaws.com:8082/swagger-ui/index.html (미배포)</br> 

#### 개발 기간 : 2023.08.21 ~ </br>  
#### 개발 인원 : 6명

## 📑 목차

1. [프로젝트 기획 배경](#프로젝트-기획-배경)
2. [주요 기능 설명](#주요-기능-설명)
3. [기술 스택](#기술-스택)
4. [프로토타입](#프로토타입)
5. [아키텍처](#아키텍처)
6. [ERD](#erd)
7. [기능 엿보기](#기능-엿보기)
8. [팀원 소개 및 역할](#팀원-소개-및-역할)

## 📌프로젝트 기획 배경

맞춤형 안전 지도 공유 서비스

## 🔎주요 기능 설명
- 취향을 바탕으로 맞춤형 코스 추천  
- 코스 커스텀 및 공유
- 위치기반 재난 솔루션
- 위험구역 제보 및 안전한 길 추천


## 🛠기술 스택

<table>
<tr>
 <td align="center">언어</td>
 <td>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/Java-orange?style=for-the-badge&logo=Java&logoColor=white"/>
	<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
	
 </td>
</tr>
<tr>
 <td align="center">프레임워크</td>
 <td>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=ffffff"/>
	<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=ffffff"/>  
</tr>
<tr>
 <td align="center">라이브러리</td>
 <td>
  
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/jwt-6DB33F?style=for-the-badge&logo=jwt&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/MUI-007FFF?style=for-the-badge&logo=MUI&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/Redux-764ABC?style=for-the-badge&logo=redux&logoColor=ffffff"/>  
<img src="https://img.shields.io/badge/Jest-C21325?style=for-the-badge&logo=Jest&logoColor=ffffff"/>  
<img src="https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=Axios&logoColor=ffffff"/>  
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=#7952B3&logoColor=ffffff"/>  
<img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=ffffff"/>

</tr>
<tr>
 <td align="center">패키지 매니저</td>
 <td>
    <img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white">
    <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

  </td>
</tr>
<tr>
 <td align="center">인프라</td>
 <td>
  <img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/kong-003459?style=for-the-badge&logo=kong&logoColor=ffffff"/>
  
</tr>
<tr>
 <td align="center">포맷팅</td>
 <td>
  <img src="https://img.shields.io/badge/ESLint-4B32C3?style=for-the-badge&logo=ESLint&logoColor=ffffff"/> 
  <img src="https://img.shields.io/badge/Prettier-F7B93E?style=for-the-badge&logo=Prettier&logoColor=ffffff"/> 
  <img src="https://img.shields.io/badge/PostCSS-DD3A0A?style=for-the-badge&logo=PostCSS&logoColor=ffffff"/> 
  </td>
</tr>

<tr>
 <td align="center">협업툴</td>
 <td>
    <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/>
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Gitlab-FC6D26?style=for-the-badge&logo=Gitlab&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=Mattermost&logoColor=white"/> 
    <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white"/>
 </td>
</tr>
<tr>
 <td align="center">기타</td>
 <td>
    <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white"/>
    <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/> 
    <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"/>
 </td>
</tr>
</table>

## 🗂 프로토타입
![main.png](./nubio/main.png){: width="10" height="10"}

![enjoy.png](./nubio/enjoy1.JPG)

![enjoy.png](./nubio/enjoy2.JPG)

![enjoy.png](./nubio/enjoy3.JPG)

![safe.PNG](./nubio/safe.PNG)

## 🧱아키텍처
![architecture.png](./nubio/architecture.png)


## 🗂ERD

![erd.png](./nubio/erd.png)





## 🧚‍♀️팀원 소개 및 역할

| 이름   | 기능 및 역할                                                                                                                                                                              |
| ------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 이서현 | 팀장 |
| 김민규 | 팀원                                        |
| 김윤욱 |  팀원                                                                                                                                                                                    |
| 윤태웅 |팀원                                                                                                                                                                                 |
| 조영재 | 팀원                                                                                                                                                                                |
| 진재환 |      팀원                                                                                                                                                                            |
