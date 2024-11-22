import React, { useEffect, useState } from "react";
import { StyleSheet, View } from "react-native";
import TabScreenWrapper from "../../../components/TabScreenWrapper";
import { usePathname } from "expo-router";

export default function DiscoverScreen() {
  const pathname = usePathname();
	const isActive = pathname === "/discover";

	return (
		<TabScreenWrapper isActive={isActive} slideDirection='right'>
			<View style={styles.container}>
				<View style={styles.square} />
			</View>
		</TabScreenWrapper>
	);
};

const styles = StyleSheet.create({
	container: {
        flex: 1, // Occupy the entire screen
        justifyContent: "center", // Center vertically
        alignItems: "center", // Center horizontally
    },
    square: {
        width: 100, // Square's width
        height: 100, // Square's height
        backgroundColor: "red", // Square's color
    },
});