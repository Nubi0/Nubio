import { useEffect } from "react";

const { Tmapv2 } = window as any;

declare global {
  interface Window {
    Tmapv2: any;
  }
}
const TMap = () => {
  useEffect(() => {
    const initTmap = () => {
      const map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(37.566481622437934, 126.98502302169841),
        width: "890px",
        height: "400px",
        zoom: 15,
      });
    };

    initTmap();
  }, []);
  return <div id="map_div"></div>;
};
export default TMap;
