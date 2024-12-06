import * as React from "react"
import Svg, { Path } from "react-native-svg"
const PrefrencesIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <Path
      stroke="#9C7CDE"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={1.667}
      d="M3.333 15h3.334M10 15h6.667M3.333 10h8.334M15 10h1.667M3.333 5h.834M7.5 5h9.167m-10 11.666v-3.333H10v3.333H6.667Zm5-5V8.333H15v3.333h-3.333Zm-7.5-5V3.333H7.5v3.333H4.167Z"
    />
  </Svg>
)
export { PrefrencesIcon }
