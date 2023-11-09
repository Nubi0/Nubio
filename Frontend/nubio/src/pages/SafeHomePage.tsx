import { SafeHomeWrapper } from "@styles/SSafeHomePage";
import Map from "@components/common/map/Map";
import Footer from "@components/common/Footer";

const SafeHomePage = () => {
  return (
    <SafeHomeWrapper>
      <Map />
      <Footer />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
