import { BrowserRouter, Routes, Route } from "react-router-dom";
import routes from "./router";
import { Provider } from "react-redux";
import { store } from "./redux/store";
import {
  ContentWrapper,
  PcWrapper,
  TitleTextWrapper,
  TitleWrapper,
  Wrapper,
} from "./styles/SPc";
import { useEffect, useState } from "react";
function App() {
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  const background = process.env.PUBLIC_URL + "/assets/pc/background.jpg";
  const safeIcon = process.env.PUBLIC_URL + "/assets/pc/safeIcon.png";
  const customIcon = process.env.PUBLIC_URL + "/assets/pc/customIcon.png";
  const recommendIcon = process.env.PUBLIC_URL + "/assets/pc/recommendIcon.png";
  const reportIcon = process.env.PUBLIC_URL + "/assets/pc/reportIcon.png";
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const checkDeviceType = () => {
      const isMobileDevice = window.innerWidth <= 500;
      setIsMobile(isMobileDevice);
    };
    window.addEventListener("resize", checkDeviceType);
    checkDeviceType();
    return () => {
      window.removeEventListener("resize", checkDeviceType);
    };
  }, []);
  return (
    <Wrapper>
      {isMobile ? (
        <Provider store={store}>
          <div
            className="App"
            style={{
              width: "430px",
              height: "932px",
              background: "white",
            }}
          >
            <BrowserRouter>
              <Routes>
                {routes.map((e) => {
                  return (
                    <Route
                      key={e.path}
                      path={e.path}
                      element={<e.Component></e.Component>}
                    />
                  );
                })}
              </Routes>
            </BrowserRouter>
          </div>
        </Provider>
      ) : (
        <>
          <img src={background} alt="배경" id="background" />
          <PcWrapper>
            <div id="check">
              <TitleWrapper>
                <img src={logo} alt="로고" id="logo" />
                <TitleTextWrapper>
                  <h1 id="safeTitle">안전하고</h1>
                  <h1 id="happyTitle">즐거운</h1>
                  <h1 id="shareTitle">데이트를 위한</h1>
                </TitleTextWrapper>
                <h1>지도 공유 서비스</h1>
              </TitleWrapper>
              <ContentWrapper>
                <div>
                  <img src={recommendIcon} alt="" />
                  <h1 id="recommendContent">
                    취향을 바탕으로 맞춤형 코스 추천
                  </h1>
                </div>
                <div>
                  <img src={customIcon} alt="" />
                  <h1 id="customContent">코스를 직접 커스텀</h1>
                </div>
                <div>
                  <img src={safeIcon} alt="" />
                  <h1 id="safeContent">목적지까지 안전한 길 추천</h1>
                </div>
                <div>
                  <img src={reportIcon} alt="" />
                  <h1 id="safeContent">위험구역 제보 공유</h1>
                </div>
              </ContentWrapper>
            </div>
          </PcWrapper>
          <Provider store={store}>
            <div
              className="App"
              style={{
                width: "430px",
                height: "932px",
                border: "0.3rem solid black",
                marginTop: "1%",
                background: "white",
              }}
            >
              <BrowserRouter>
                <Routes>
                  {routes.map((e) => {
                    return (
                      <Route
                        key={e.path}
                        path={e.path}
                        element={<e.Component></e.Component>}
                      />
                    );
                  })}
                </Routes>
              </BrowserRouter>
            </div>
          </Provider>
        </>
      )}
    </Wrapper>
  );
}

export default App;
