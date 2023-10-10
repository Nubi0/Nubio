import styled from "@emotion/styled";

export const Wrapper = styled.div`
  width: 100vw;
  display: flex;
  #background{
    position:absolute;
    z-index:-9999;
    width:100%;
    height:100%;
    opacity: 0.3;
  }
  }
`;

export const PcWrapper = styled.div`
  width: 65%;
  height: 50%;
  // background: red;
`;

// 제목
export const TitleWrapper = styled.div`
  margin-top: 5%;
  text-align: center;
  img {
    width: 30rem;
  }
  h1 {
    font-size: 3rem;
    margin: 0 0.5rem;
  }
`;

export const TitleTextWrapper = styled.div`
  display: flex;
  justify-content: center;
  #safeTitle {
    color: #41d992;
  }
  #happyTitle {
    color: #ffc542;
  }
`;

// 내용

export const ContentWrapper = styled.div`
  margin: 5%;
  div {
    display: flex;
    justify-content: center;
    margin: 1rem 0;
  }
  img {
    width: 8%;
    margin: 0 5%;
  }
  #recommendContent {
    font-size: 2.5rem;
  }
  #customContent {
    font-size: 2.5rem;
  }
  #safeContent {
    font-size: 2.5rem;
  }
`;
