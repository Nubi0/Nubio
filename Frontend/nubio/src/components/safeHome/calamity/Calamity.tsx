import { CalamityWrapper } from "../../../styles/SSafeHomePage";
import CalamityMessage from "./CalamityMessage";

const Calamity = ({ setIsReceiveMessage }: any) => {
  return (
    <CalamityWrapper>
      <CalamityMessage setIsReceiveMessage={setIsReceiveMessage} />
    </CalamityWrapper>
  );
};
export default Calamity;
