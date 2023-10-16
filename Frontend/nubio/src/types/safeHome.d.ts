// 재해 대피 아이콘
interface DisasterIconInfo {
  src: string;
  name: string;
  content: string;
}

interface propsType {
  position: Array;
}

// 빠른 길 찾기
interface StartCoordinates {
  x: number;
  y: number;
}
interface EndCoordinates {
  x: number;
  y: number;
}

// 안전한 길
interface SafeDirectionProps {
  clearRoute: () => void;
  setFindRouteOpen: (findRouteOpen: boolean) => void;
}
interface StartCoordinates {
  x: number;
  y: number;
}
interface EndCoordinates {
  x: number;
  y: number;
}
