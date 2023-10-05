import styled from "@emotion/styled";

export const Wrapper = styled.div`
  width: 100vw;
  display: flex;
`;

export const PcWrapper = styled.div`
  width: 70%;
`;

// 제목
export const TitleWrapper = styled.div`
  text-align: center;
  img {
    width: 30rem;
  }
  h1 {
    margin: 0;
    font-size: 3rem;
  }
`;

export const TitleTextWrapper = styled.div`
  display: flex;
  justify-content: center;
  #safe {
    color: #41d992;
  }
  #map {
    color: #ffc542;
  }
`;

// 내용

export const ContentWrapper = styled.div`
  #recommend {
    position: absolute;
    top: 30%;
    font-size: 2.5rem;
    transform: rotate(-10deg);
  }
  #custom {
    position: absolute;
    top: 40%;
    font-size: 2.5rem;
    transform: rotate(-10deg);
  }
`;
