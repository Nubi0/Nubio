import { RouteInfoWrapper } from "@styles/SRoute";
import SafeDistanceInfo from "./safe/SafeDistanceInfo";
import ShortDirectionInfo from "./short/ShortDirectionInfo";

const RouteInfo = () => {
  return (
    <RouteInfoWrapper>
      <ShortDirectionInfo />
      <SafeDistanceInfo />
    </RouteInfoWrapper>
  );
};
export default RouteInfo;
