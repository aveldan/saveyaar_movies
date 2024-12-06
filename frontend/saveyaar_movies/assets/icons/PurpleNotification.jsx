import * as React from "react"
import Svg, { Mask, Path, G } from "react-native-svg"
const PurpleNotification = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <Mask
      id="a"
      width={15}
      height={15}
      x={2}
      y={2}
      maskUnits="userSpaceOnUse"
      style={{
        maskType: "luminance",
      }}
    >
      <Path
        fill="#fff"
        fillRule="evenodd"
        d="M2.271 2.902h14.447v13.241H2.27V2.903Z"
        clipRule="evenodd"
      />
    </Mask>
    <G mask="url(#a)">
      <Path
        fill="#9C7CDE"
        fillRule="evenodd"
        d="M9.493 4.049c-2.73 0-4.632 2.09-4.632 3.965 0 1.587-.45 2.32-.849 2.968-.32.52-.572.93-.572 1.822C3.57 14.244 4.543 15 9.493 15c4.923 0 5.926-.79 6.056-2.245-.003-.843-.255-1.254-.574-1.773-.399-.648-.85-1.381-.85-2.968 0-1.875-1.902-3.965-4.632-3.965Zm0 12.095c-3.652 0-6.952-.252-7.222-3.292-.002-1.258.39-1.898.738-2.461.35-.571.68-1.11.68-2.379 0-2.467 2.333-5.11 5.804-5.11 3.472 0 5.804 2.643 5.804 5.11 0 1.27.33 1.808.681 2.379.347.563.74 1.203.74 2.412-.273 3.089-3.572 3.34-7.225 3.34Z"
        clipRule="evenodd"
      />
    </G>
    <Path
      fill="#9C7CDE"
      fillRule="evenodd"
      d="M9.456 19.314h-.002c-.875 0-1.704-.378-2.333-1.062a.562.562 0 0 1 .044-.808.594.594 0 0 1 .827.043c.405.44.924.682 1.463.682.542 0 1.064-.242 1.47-.683a.596.596 0 0 1 .826-.042c.24.212.26.574.044.809-.631.684-1.461 1.061-2.34 1.061Z"
      clipRule="evenodd"
    />
  </Svg>
)
export { PurpleNotification }
