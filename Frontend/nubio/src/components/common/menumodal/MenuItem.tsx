import { useState } from "react";
import { MenuItemWrapper } from "../../../styles/SFooter";
import CreateReport from "../../safeHome/report/CreateReport";
import Shelter from "../../safeHome/calamity/Shelter";

const MenuItem = ({ name }: { name: string }) => {
  const [report, setReport] = useState(false);
  const [shelter, setShelter] = useState(false);
  const [calamity, setCalamity] = useState(false);

  // 해당 메뉴에 따라 상태를 업데이트합니다.
  const handleMenuClick = (menuName: string) => {
    if (menuName === "재난 문자 조회") {
      setCalamity(true);
      setReport(false);
      setShelter(false);
    } else if (menuName === "제보 기능") {
      setCalamity(false);
      setReport(true);
      setShelter(false);
    } else if (menuName === "대피소 찾기") {
      setCalamity(false);
      setReport(false);
      setShelter(true);
    }
  };

  return (
    <MenuItemWrapper onClick={() => handleMenuClick(name)}>
      {report && <CreateReport /> && name}
      {shelter && <Shelter /> && name}
      {!report && !shelter && !calamity && name}
    </MenuItemWrapper>
  );
};

export default MenuItem;
