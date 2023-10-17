// 재해 대피 아이콘
type DisasterIconInfo = {
  src: string;
  name: string;
  content: string;
};

type propsType = {
  position: Array;
};

// 재난메세지
type EmergencyMessage = {
  city: string;
  county: string;
  message: string;
  md_id: number;
  emer_type: string;
  occurred_time: string;
};

// 안전한길
type SafeDirectionProps = {
  clearRoute: () => void;
  setFindRouteOpen: (findRouteOpen: boolean) => void;
};
type StartCoordinates = {
  x: number;
  y: number;
};
type EndCoordinates = {
  x: number;
  y: number;
};

// 빠른 길
type ShortDirectionProps = {
  clearRoute: () => void;
  setFindRouteOpen: (findRouteOpen: boolean) => void;
};
type StartCoordinates = {
  x: number;
  y: number;
};
type EndCoordinates = {
  x: number;
  y: number;
};
