import styled from "styled-components";

// 길 찾기 결과
export const RouteInfoWrapper = styled.div`
  position: absolute;
  bottom: 7%;
  width: 100%;
  display: flex;
  justify-content: space-around;
  z-index: 2;
`;
// 안전경로 찾기 버튼
export const SafeDirectionButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
  margin-right: 0.5rem;
`;

// 안심경로 안내
export const SafeDistanceWrapper = styled.div`
  width: 40%;
  border-radius: 1rem;
  background: #33ff57;
  padding: 0.5rem;
  p {
    margin: 0.5rem;
    color: white;
  }
`;

// 빠른길 찾기 버튼
export const ShortDirectionButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
  margin-right: 0.5rem;
`;

// 빠른 길 안내
export const ShortDistanceWrapper = styled.div`
  width: 40%;
  background: red;
  border-radius: 1rem;
  padding: 0.5rem;
  p {
    margin: 0.5rem;
    color: white;
  }
`;
