import {
  SafeModalWrapper,
  ModalTitle,
  MenuList,
} from "../../../styles/SFooter";
import CloseButton from "./CloseButton";
import Shelter from "../../safeHome/calamity/Shelter";
import CreateReport from "../../safeHome/report/CreateReport";
import CalamityMessage from "../../safeHome/calamity/CalamityMessageMenu";

const SafeModal = ({ setActive }: { setActive: (value: boolean) => void }) => {
  const safeImg = process.env.PUBLIC_URL + "/assets/menu/menuSafe.png";

  return (
    <SafeModalWrapper>
      <ModalTitle>
        <h2>안전</h2>
        <img src={safeImg} style={{ width: "3.5rem", height: "3.5rem" }} />
      </ModalTitle>
      <MenuList>
        <CalamityMessage />
        <CreateReport />
        <Shelter />
      </MenuList>
      <CloseButton setModal={() => setActive(false)} />
    </SafeModalWrapper>
  );
};

export default SafeModal;
