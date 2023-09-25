import { RouteInfoWrapper } from "../../../styles/SSafeHomePage";
import SafeDistance from "./safe/SafeDistance";
import ShortDistance from "./short/ShortDistance";

const RouteInfo = () => {
  return (
    <RouteInfoWrapper>
      <ShortDistance />
      <SafeDistance />
    </RouteInfoWrapper>
  );
};
export default RouteInfo;
