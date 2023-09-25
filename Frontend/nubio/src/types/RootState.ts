import MapSlice from "../redux/slice/MapSlice";
import EnjoySlice from "../redux/slice/EnjoySlice";

export type RootState = {
  map: ReturnType<typeof MapSlice>;
  enjoy: ReturnType<typeof EnjoySlice>;
};

export default RootState;
