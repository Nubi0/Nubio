import { RootInfoWrapper } from "../../../styles/SSafeHomePage";
import SafeDistance from "./SafeDistance";
import ShortDistance from "./ShortDistance";

const RootInfo = () => {
  return (
    <RootInfoWrapper>
      <ShortDistance />
      <SafeDistance />
    </RootInfoWrapper>
  );
};
export default RootInfo;
