public interface DemandReadingService {

    DemandReading createReading(DemandReading reading);

    DemandReading getLatestReading(Long zoneId);

    List<DemandReading> getReadingsForZone(Long zoneId);

    List<DemandReading> getRecentReadings(Long zoneId, int limit);
}
