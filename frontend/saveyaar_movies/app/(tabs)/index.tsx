import React, { useEffect, useState } from "react";
import {  useRouter } from "expo-router";
import { usePathname } from "expo-router";
import Animated, {  useAnimatedScrollHandler, useSharedValue, withTiming } from "react-native-reanimated";

import TabScreenWrapper from "../../components/TabScreenWrapper";
import { MoviesData, MovieType, MovieListType} from "@/components/MovieList";
import { styles } from "@/styles";
import HomeScreenHeader from "@/components/HomeScreenHeader";
import HomeView from "@/components/HomeView";
import { CONTENT_URL, TMDB_KEY } from "@env";

export default function HomeScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/" || pathname == "/index";
	const scrollDirection = useSharedValue(0);

	const SCROLL_THRESHOLD = 4;
	const SLIDE_ACTIVATION_POINT = 90;
	const scrollY = useSharedValue(0);
	const lastScrollY = useSharedValue(0);

	const [movieData, setMovieData] = useState<MoviesData | null>(null);


	const handlePressMidTab1 = (text: string) => {
		setMidTab1((prevTabs) => (
			prevTabs.map((tab) => (
				tab.text === text? {...tab, isActive: true} : {...tab, isActive: false}
			))
		));
	};

	const [midTab1, setMidTab1] = useState([
		{
			text: "Movies",
			handleOnPress: () => handlePressMidTab1("Movies"),
			isActive: true,
		},
		{
			text: "TV Shows",
			handleOnPress: () => handlePressMidTab1("TV Shows"),
			isActive: false,
		}
	]);


	const handleOnPressMidTab2 = (text: string) => {
		setMidTab2((prevTabs) => (
			prevTabs.map((tab) => (
				tab.text === text ? {...tab, isActive: true} : {...tab, isActive: false}
			))
		));
	}

	const [midTab2, setMidTab2] = useState([
		{
			text: "This Week",
			handleOnPress: () => handleOnPressMidTab2("This Week"),
			isActive: false,
		},
		{
			text: "This Month",
			handleOnPress: () => handleOnPressMidTab2("This Month"),
			isActive: true
		},
		{
			text: "Upcoming",
			handleOnPress: () => handleOnPressMidTab2("Upcoming"),
			isActive: false,
		}
	]);


	useEffect(() => {
		let content = "movies";
		const mapedValues: { [key: string]: string } = {
			"Movies": "movies",
			"TV Shows": "tvs"
		};

		midTab1.forEach(tab => {
			if(tab.isActive){
				content = mapedValues[tab.text]
			}
		});

		const url = new URL(`${CONTENT_URL}/${content}`);

		midTab2.forEach(tab => {
			if(tab.isActive){
				url.searchParams.append('release_date', tab.text);
			}
		});

		const fetch_content = async () => {
			const response = await fetch(url.toString());
			if(response.status === 200) {
				const data: MoviesData = await response.json();
				setMovieData(data)
			} else {
				console.error(`Error: Received status code ${response.status} when trying to access content`);
			}
		};

		fetch_content();
	}, [midTab1, midTab2]);


	const scrollHandler = useAnimatedScrollHandler({
		onScroll: (event) => {
		  const currentScrollY = event.contentOffset.y;
		  const scrollDelta = currentScrollY - lastScrollY.value;
	
		  // Only trigger direction change if we've scrolled past SLIDE_ACTIVATION_POINT
		  if (currentScrollY >= SLIDE_ACTIVATION_POINT) {
			if (scrollDelta > SCROLL_THRESHOLD) {
			  // Scrolling down - hide tabs
			  scrollDirection.value = withTiming(1, { duration: 400 });
			} else if (scrollDelta < -SCROLL_THRESHOLD) {
			  // Scrolling up - show tabs
			  scrollDirection.value = withTiming(0, { duration: 400 });
			}
		  } else {
			// Before SLIDE_ACTIVATION_POINT, always show tabs
			scrollDirection.value = withTiming(0, { duration: 400 });
		  }
	
		  lastScrollY.value = currentScrollY;
		  scrollY.value = currentScrollY;
		},
	  });
	  
	const router = useRouter();
	const handleProfile = () => {
		router.push("/userProfile");
    };

	const handleNotification = () => {
		router.push("/notifications")
	};
		
	return (
		<TabScreenWrapper isActive={isActive} slideDirection="right">
			<Animated.ScrollView 
				style={styles.scrollView} 
				onScroll={scrollHandler}
				scrollEventThrottle={16}
				showsVerticalScrollIndicator={false}
				bounces={false}
			>
				<HomeScreenHeader 
					handleProfile={handleProfile}
					handleNotification={handleNotification}
				/>
				<HomeView movieData={movieData} midTab1={midTab1} midTab2={midTab2}/>
			</Animated.ScrollView>
		</TabScreenWrapper>
	);
};